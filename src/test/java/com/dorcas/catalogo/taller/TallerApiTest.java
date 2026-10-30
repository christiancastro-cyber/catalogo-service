package com.dorcas.catalogo.taller;

import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class TallerApiTest {

    @Autowired
    MockMvc mockMvc;

    private String alumna(String nombre) {
        return """
                {"alumna": "%s"}
                """.formatted(nombre);
    }

    private Long apuntarYDevolverId(Long tallerId, String nombre) throws Exception {
        String respuesta = mockMvc.perform(post("/api/talleres/{id}/reservas", tallerId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(alumna(nombre)))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();
        return JsonPath.parse(respuesta).read("$.id", Long.class);
    }

    @Test
    void listarDevuelveLosCuatroTalleres() throws Exception {
        mockMvc.perform(get("/api/talleres"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(4));
    }

    @Test
    void filtrarPorNivelDevuelveSoloEseNivel() throws Exception {
        mockMvc.perform(get("/api/talleres").param("nivel", "INICIACION"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3))
                .andExpect(jsonPath("$[*].nivel", everyItem(is("INICIACION"))));
    }

    @Test
    void verUnTallerQueNoExisteDevuelve404() throws Exception {
        mockMvc.perform(get("/api/talleres/99"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.codigo").value("TALLER_NO_ENCONTRADO"));
    }

    @Test
    void apuntarUnaAlumnaDevuelve201ConLaReserva() throws Exception {
        mockMvc.perform(post("/api/talleres/1/reservas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(alumna("Mara")))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(jsonPath("$.alumna").value("Mara"))
                .andExpect(jsonPath("$.tallerId").value(1))
                .andExpect(jsonPath("$.id").isNumber());
    }

    @Test
    void apuntarDosVecesLaMismaAlumnaDevuelve409() throws Exception {
        apuntarYDevolverId(1L, "Mara");

        mockMvc.perform(post("/api/talleres/1/reservas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(alumna("Mara")))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.codigo").value("ALUMNA_YA_APUNTADA"));
    }

    @Test
    void apuntarSinNombreDevuelve400() throws Exception {
        mockMvc.perform(post("/api/talleres/1/reservas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(alumna("")))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.codigo").value("DATOS_NO_VALIDOS"));
    }

    @Test
    void apuntarEnUnTallerLlenoDevuelve409() throws Exception {
        for (String nombre : new String[]{"Ana", "Lucía", "Rosa", "Elena"}) {
            apuntarYDevolverId(4L, nombre);
        }

        mockMvc.perform(post("/api/talleres/4/reservas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(alumna("Pilar")))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.codigo").value("TALLER_COMPLETO"));
    }

    @Test
    void quitarUnaReservaLaBorraDeLaLista() throws Exception {
        Long reservaId = apuntarYDevolverId(1L, "Mara");

        mockMvc.perform(delete("/api/talleres/1/reservas/{reservaId}", reservaId))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/api/talleres/1/reservas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));
    }

    @Test
    void quitarUnaReservaQueNoExisteDevuelve404() throws Exception {
        mockMvc.perform(delete("/api/talleres/1/reservas/999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.codigo").value("RESERVA_NO_ENCONTRADA"));
    }
}