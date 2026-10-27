package com.dorcas.catalogo.tela;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TelaRepository extends JpaRepository<Tela, Long> {

    boolean existsByReferenciaIgnoreCase(String referencia);

    boolean existsByReferenciaIgnoreCaseAndIdNot(String referencia, Long id);

    @Query("""
            select t from Tela t
            where (:categoria is null or t.categoria = :categoria)
              and (:aptaPrincipiantes is null or t.aptaPrincipiantes = :aptaPrincipiantes)
          """)
    List<Tela> buscar(Categoria categoria, Boolean aptaPrincipiantes);

    List<Tela> findByStockMetrosLessThanOrderByStockMetros(double minimo);
}
