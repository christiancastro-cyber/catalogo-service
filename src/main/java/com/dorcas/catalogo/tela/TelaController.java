package com.dorcas.catalogo.tela;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/telas")
public class TelaController {
    private final TelaService telaService;

    public TelaController(TelaService telaService) {
        this.telaService = telaService;
    }

    @PostMapping
    public ResponseEntity<TelaOutputDto> crear(@Valid @RequestBody TelaInputDto input){
        Tela tela = telaService.crear(input);
        return ResponseEntity
                .created(URI.create("/api/telas/" + tela.getId()))
                .body(TelaOutputDto.desde(tela));
    }

    @PutMapping("/{id}")
    public TelaOutputDto modificar(@PathVariable Long id, @Valid @RequestBody TelaInputDto input){
        return TelaOutputDto.desde(telaService.modificar(id, input));
    }

    @PatchMapping("/{id}")
    public TelaOutputDto cambiarPrecio(@PathVariable Long id, @Valid @RequestBody CambioPrecioInputDto input){
        return TelaOutputDto.desde(telaService.cambiarPrecio(id, input.precioMetro()));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void borrar(@PathVariable Long id){
        telaService.borrar(id);
    }

    @PostMapping("/{id}/ventas")
    @ResponseStatus(HttpStatus.CREATED)
    public VentaOutputDto vender(@PathVariable Long id, @Valid @RequestBody VentaInputDto input){
        return telaService.vender(id, input.metros());
    }


    @GetMapping
    public List<TelaOutputDto> listar(@RequestParam(required= false) Categoria categoria, @RequestParam(required = false) Boolean aptaPrincipiantes){
        return telaService.listar(categoria, aptaPrincipiantes).stream()
                .map(TelaOutputDto::desde)
                .toList();
    }

    @GetMapping("/{id}")
    public TelaOutputDto ver(@PathVariable Long id){
        return TelaOutputDto.desde(telaService.buscarPorId(id));
    }

    @GetMapping("/{id}/ventas")
    public List<VentaOutputDto> listarVentas(@PathVariable Long id){
        return telaService.listarVentas(id);
    }




}
