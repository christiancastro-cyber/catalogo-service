package com.dorcas.catalogo.taller.dominio;

import java.util.List;
import java.util.Optional;

public interface TallerRepositorio {

    List<Taller> buscarTodos();

    List<Taller> buscarPorNivel(Nivel nivel);

    Optional<Taller> buscarPorId(Long id);

    Reserva guardarReserva(Reserva reserva);
}