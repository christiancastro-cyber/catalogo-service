package com.dorcas.catalogo.taller.aplicacion;

import com.dorcas.catalogo.taller.dominio.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TallerService {

    private final TallerRepositorio repositorio;

    public TallerService(TallerRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public List<Taller> listar(Nivel nivel, Boolean conPlazas) {
        List<Taller> talleres = (nivel == null)
                ? repositorio.buscarTodos()
                : repositorio.buscarPorNivel(nivel);

        return talleres.stream()
                .filter(t -> conPlazas == null || !t.estaCompleto() == conPlazas)
                .toList();
    }

    public Taller buscarPorId(Long id) {
        return repositorio.buscarPorId(id)
                .orElseThrow(() -> new TallerNoEncontradoException(id));
    }

    public List<Reserva> listarReservas(Long tallerId) {
        return buscarPorId(tallerId).getReservas();
    }

    @Transactional
    public Reserva apuntar(Long tallerId, String alumna) {
        Taller taller = buscarPorId(tallerId);
        Reserva reserva = taller.apuntar(alumna);
        return repositorio.guardarReserva(reserva);
    }

    @Transactional
    public void quitar(Long tallerId, Long reservaId) {
        buscarPorId(tallerId).quitar(reservaId);
    }
}