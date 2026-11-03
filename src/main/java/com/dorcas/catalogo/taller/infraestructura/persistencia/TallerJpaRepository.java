package com.dorcas.catalogo.taller.infraestructura.persistencia;

import com.dorcas.catalogo.taller.dominio.Nivel;
import com.dorcas.catalogo.taller.dominio.Taller;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface TallerJpaRepository extends JpaRepository<Taller, Long> {
    List<Taller> findByNivel(Nivel nivel);
}
