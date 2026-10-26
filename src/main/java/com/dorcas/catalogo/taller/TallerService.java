package com.dorcas.catalogo.taller;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
class TallerService {
    private final List<Taller> talleres = new ArrayList<>();
    private final List<Reserva> reservas = new ArrayList<>();
    private long siguienteIdReserva = 1;

    public TallerService() {
        talleres.add(new Taller(1L, "Taller de Canastilla", Nivel.INICIACION, 8, 45.0));
        talleres.add(new Taller(2L, "Patronaje Básico", Nivel.INICIACION, 5, 50.0));
        talleres.add(new Taller(3L, "Costura Básica", Nivel.INICIACION, 5, 55.0));
        talleres.add(new Taller(4L, "Corsetería", Nivel.INTERMEDIO, 2, 70.0));
    }

    public List<Taller> listar(Nivel nivel, Boolean conPlazas){
        return talleres.stream()
                .filter(t-> nivel == null || t.getNivel() == nivel)
                .filter(t-> conPlazas == null || t.estaCompleto() == conPlazas)
                .toList();
    }

    public Taller buscarPorId(Long id){
        return talleres.stream()
                .filter(t->t.getId().equals(id))
                .findFirst()
                .orElseThrow(()->new TallerNoEncontradoException(id));
    }

    public List<Reserva> listarReservas(Long tallerId){
        Taller taller = buscarPorId(tallerId);
        return taller.getReservas();
    }

    public Reserva apuntar(Long tallerId, String alumna){
        Taller taller = buscarPorId(tallerId);
        return  taller.apuntar(siguienteIdReserva++,alumna);
    }


    public void quitar(Long tallerId, Long reservaId){
        Taller taller = buscarPorId(tallerId);
        taller.quitar(reservaId);
    }




}
