package com.dorcas.catalogo.taller.infraestructura.persistencia;

import com.dorcas.catalogo.taller.dominio.Nivel;
import com.dorcas.catalogo.taller.dominio.Reserva;
import com.dorcas.catalogo.taller.dominio.Taller;
import com.dorcas.catalogo.taller.dominio.TallerRepositorio;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
class TallerRepositorioJpa implements TallerRepositorio {

    private final TallerJpaRepository talleres;
    private final ReservaJpaRepository reservas;

    public TallerRepositorioJpa(TallerJpaRepository talleres, ReservaJpaRepository reservas) {
        this.talleres = talleres;
        this.reservas = reservas;
    }

    @Override
    public List<Taller> buscarTodos() {
        return talleres.findAll();
    }

    @Override
    public List<Taller> buscarPorNivel(Nivel nivel) {
        return talleres.findByNivel(nivel);
    }

    @Override
    public Optional<Taller> buscarPorId(Long id) {
        return talleres.findById(id);
    }

    @Override
    public Reserva guardarReserva(Reserva reserva) {
        return reservas.save(reserva);
    }
}
