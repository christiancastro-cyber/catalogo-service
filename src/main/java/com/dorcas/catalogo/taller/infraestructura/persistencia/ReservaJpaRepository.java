package com.dorcas.catalogo.taller.infraestructura.persistencia;

import com.dorcas.catalogo.taller.dominio.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

interface ReservaJpaRepository extends JpaRepository<Reserva, Long> {
}
