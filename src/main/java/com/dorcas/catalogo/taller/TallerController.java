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
    private final TelaService telaService;

    public TallerController(TallerService tallerService, TelaService telaService) {this.tallerService = tallerService;
        this.telaService = telaService;
    }

    @PostMapping("/{id}/reservas")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<TallerOutputDto> crear(@PathVariable Long id, @Valid @RequestBody Reserva input){
        Reserva reserva = tallerService.apuntar(id, input.alumna());
        Taller taller = tallerService.buscarPorId(id);

        return ResponseEntity
                .created(URI.create("api/talleres/" + id + "/reservas/" +reserva.id()))
                .body(TallerOutputDto.desde(taller));
    }


    @GetMapping
    public List<TallerOutputDto> listar(@RequestParam(required = false) Nivel nivel, @RequestParam(required = false) Boolean conPlazas){
        return tallerService.listar(nivel, conPlazas).stream()
                .map(TallerOutputDto::desde)
                .toList();
    }


    @GetMapping("{id}")
    public TallerOutputDto ver(@PathVariable Long id){return TallerOutputDto.desde(tallerService.buscarPorId(id));}

    @GetMapping("/{id}/reservas")
    public List<Reserva> listarReservas(@PathVariable Long id){return tallerService.listarReservas(id);}

    @DeleteMapping("/{id}/reservas/{reservaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void borrar(@PathVariable Long id, @PathVariable Long reservaId){
        tallerService.quitar(id, reservaId);
    }


}
