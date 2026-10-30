package com.dorcas.catalogo.tela;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class TelaApiTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void listarDevuelveLasOchoTelas() throws Exception {
        mockMvc.perform(get("/api/telas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(8));

    }

    @Test
    void filtrarPorCategoriaDevuelveSoloEsaCategoria() throws Exception {
        mockMvc.perform(get("/api/telas").param("categoria", "HOGAR"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3))
                .andExpect(jsonPath("$[*].categoria", everyItem(is("HOGAR"))));
    }

    @Test
    void verUnaTelaQueNoExisteDevuelve404() throws Exception {
        mockMvc.perform(get("/api/telas/99"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.codigo").value("TELA_NO_ENCONTRADA"));
    }

    @Test
    void darDeAltaUnaTelaDevuelve201ConLocation() throws Exception {
        String cuerpo = """
                {
                  "referencia": "MUS-02",
                  "nombre": "Muselina estampada",
                  "categoria": "INFANTIL",
                  "precioMetro": 8.50,
                  "stockMetros": 25,
                  "aptaPrincipiantes": true
                }
                """;

        mockMvc.perform(post("/api/telas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(cuerpo))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(jsonPath("$.referencia").value("MUS-02"))
                .andExpect(jsonPath("$.id").isNumber());
    }

    @Test
    void darDeAltaConDatosMalDevuelve400DatosNoValidos() throws Exception {
        String cuerpo = """
                {
                  "referencia": "MUS-02",
                  "nombre": "",
                  "categoria": "INFANTIL",
                  "precioMetro": -3,
                  "stockMetros": 25,
                  "aptaPrincipiantes": true
                }
                """;

        mockMvc.perform(post("/api/telas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(cuerpo))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.codigo").value("DATOS_NO_VALIDOS"))
                .andExpect(jsonPath("$.detalles.length()").value(2));
    }

    @Test
    void darDeAltaConValoresQueNoEncajanDevuelve400JsonNoValido() throws Exception {
        String cuerpo = """
            {
              "referencia": "MUS-02",
              "nombre": "Muselina estampada",
              "categoria": "CORTINA",
              "precioMetro": "otro",
              "stockMetros": 25,
              "aptaPrincipiantes": true
            }
            """;

        mockMvc.perform(post("/api/telas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(cuerpo))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.codigo").value("JSON_NO_VALIDO"));
    }

    @Test
    void darDeAltaConReferenciaExistente() throws Exception {
        String cuerpo = """
            {
              "referencia": "ALG-01",
              "nombre": "Muselina estampada",
                  "categoria": "INFANTIL",
                  "precioMetro": 3,
              "stockMetros": 25,
              "aptaPrincipiantes": true
            }
            """;

        mockMvc.perform(post("/api/telas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(cuerpo))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.codigo").value("REFERENCIA_DUPLICADA"));
    }

    @Test
    void venderSumaLosMetrosAlTotalVendido() throws Exception{

        String cuerpo = """
                {
                  "metros": 2
                }
                """;

        mockMvc.perform(post("/api/telas/1/ventas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(cuerpo))
                .andExpect(status().isCreated());

        mockMvc.perform(get("/api/telas/1/ventas/total"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.metrosVendidos").value(2.0));

    }

    @Test
    void borrarUnaTelaConVentasDevuelve409() throws Exception{
        String cuerpo = """
                {
                  "metros": 2
                }
                """;

        mockMvc.perform(post("/api/telas/1/ventas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(cuerpo))
                .andExpect(status().isCreated());

        mockMvc.perform(delete("/api/telas/1"))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.codigo").value("TELA_CON_VENTAS"));
    }

}
