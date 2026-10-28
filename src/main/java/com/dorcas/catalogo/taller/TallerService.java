package com.dorcas.catalogo.taller;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TallerService {

    private final TallerRepository tallerRepository;
    private final ReservaRepository reservaRepository;


    public TallerService(TallerRepository tallerRepository, ReservaRepository reservaRepository) {
        this.tallerRepository = tallerRepository;
        this.reservaRepository = reservaRepository;
    }

    public List<Taller> listar(Nivel nivel, Boolean conPlazas) {
        List<Taller> talleres = (nivel == null)
                ? tallerRepository.findAll()
                : tallerRepository.findByNivel(nivel);

        return talleres.stream()
                .filter(t -> conPlazas == null || !t.estaCompleto() == conPlazas)
                .toList();
    }

    public Taller buscarPorId(Long id) {
        return tallerRepository.findById(id)
                .orElseThrow(()-> new TallerNoEncontradoException(id));
    }

    public List<Reserva> listarReservas(Long tallerId) {
        Taller taller = buscarPorId(tallerId);
        return taller.getReservas();
    }

    @Transactional
    public Reserva apuntar(Long tallerId, String alumna) {
        Taller taller = buscarPorId(tallerId);
        Reserva reserva = taller.apuntar(alumna);
        return reservaRepository.save(reserva);
    }


    public void quitar(Long tallerId, Long reservaId) {
        Taller taller = buscarPorId(tallerId);
        taller.quitar(reservaId);
    }


}
