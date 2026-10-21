package com.dorcas.catalogo.tela;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/telas")
public class TelaController {
    private final TelaService telaService;

    public TelaController(TelaService telaService) {
        this.telaService = telaService;
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




}
