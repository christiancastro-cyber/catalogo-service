package com.dorcas.catalogo.taller;

import com.dorcas.catalogo.tela.TelaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/talleres")
public class TallerController {
    private final TallerService tallerService;

    public TallerController(TallerService tallerService) {
        this.tallerService = tallerService;
    }

    @GetMapping
    public List<TallerOutputDto> listar(@RequestParam(required = false) Nivel nivel,
                                        @RequestParam(required = false) Boolean conPlazas) {
        return tallerService.listar(nivel, conPlazas).stream()
                .map(TallerOutputDto::desde)
                .toList();
    }

    @GetMapping("{id}")
    public TallerOutputDto ver(@PathVariable Long id) {
        return TallerOutputDto.desde(tallerService.buscarPorId(id));
    }

    @GetMapping("/{id}/reservas")
    public List<ReservaOutputDto> listarReservas(@PathVariable Long id) {
        return tallerService.listarReservas(id).stream()
                .map(reserva -> ReservaOutputDto.desde(id, reserva))
                .toList();
    }


    @PostMapping("/{id}/reservas")
    public ResponseEntity<ReservaOutputDto> apuntar(@PathVariable Long id,
                                                    @Valid @RequestBody ReservaInputDto input) {
        Reserva reserva = tallerService.apuntar(id, input.alumna());

        return ResponseEntity
                .created(URI.create("api/talleres/" + id + "/reservas/" + reserva.id()))
                .body(ReservaOutputDto.desde(id, reserva));
    }

    @DeleteMapping("/{id}/reservas/{reservaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void borrar(@PathVariable Long id, @PathVariable Long reservaId) {
        tallerService.quitar(id, reservaId);
    }


}
