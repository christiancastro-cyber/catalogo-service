package com.dorcas.catalogo.tela;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

interface VentaRepository extends JpaRepository<Venta, Long> {

    List<Venta> findByTelaIdOrderByIdAsc(Long telaId);

    boolean existsByTelaId(Long telaId);

    @Query("select coalesce(sum(v.metros), 0.0) from Venta v where v.tela.id = :telaId")
    double sumarMetrosVendidos(Long telaId);


}
