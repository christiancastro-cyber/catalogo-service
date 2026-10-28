package com.dorcas.catalogo.taller;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface TallerRepository extends JpaRepository<Taller, Long> {
    List<Taller> findByNivel(Nivel nivel);
}
