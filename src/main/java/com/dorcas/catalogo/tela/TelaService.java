package com.dorcas.catalogo.tela;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TelaService {
    private final List<Tela> telas = new ArrayList<>();

    public TelaService(){
        telas.add(new Tela(1L,"ALG-01","Algodón",Categoria.INFANTIL ,8.50, 15.0, true));
        telas.add(new Tela(2L, "LON-01","Loneta Lisa", Categoria.HOGAR,11.0, 6, true));
        telas.add(new Tela(3L,"LON-02","Loneta estampada",Categoria.HOGAR, 9.30, 5, true));
        telas.add(new Tela(4L, "LY-01","Lycra", Categoria.MODA,8.50, 15.5, false));
        telas.add(new Tela(5L, "STR-01","Stretch", Categoria.MODA,4.5, 9, true));
        telas.add(new Tela(6L,"ANT-01","Antelina", Categoria.HOGAR, 6.0, 12.5, false));
        telas.add(new Tela (7L,"LAM-01","Lamé", Categoria.FIESTA,5.0, 6.5, false));
        telas.add(new Tela(8L, "COR-01","Coralina", Categoria.ABRIGO, 11.50, 8, false));
    }

    public List<Tela> listar(Categoria categoria, Boolean aptaPrincipiantes){

        return telas.stream()
                .filter(t -> categoria == null || t.getCategoria() == categoria)
                .filter( t -> aptaPrincipiantes == null || t.esAptaPrincipiantes() == aptaPrincipiantes)
                .toList();
    }

    public Tela buscarPorId(Long id){
        return telas.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new TelaNoEncontradaException(id));
    }

}
