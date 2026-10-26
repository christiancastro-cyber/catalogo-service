package com.dorcas.catalogo.taller;

import java.util.ArrayList;
import java.util.List;

public class Taller {
    private final Long id;
    private final String nombre;
    private final Nivel nivel;
    private final int plazas;
    private final double precio;
    private final List<Reserva> reservas = new ArrayList<>();

    public Taller(Long id, String nombre, Nivel nivel, int plazas, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.nivel = nivel;
        this.plazas = plazas;
        this.precio = precio;
    }

    public Reserva apuntar(Long reservaId, String alumna) {
        if (estaCompleto()) {
            throw new TallerCompletoException(nombre, plazas);
        }
        boolean yaApuntada = reservas.stream()
                .anyMatch(r -> r.alumna().equalsIgnoreCase(alumna));
        if (yaApuntada) {
            throw new AlumnaYaApuntadaException(alumna, nombre);
        }
        Reserva reserva = new Reserva(reservaId, alumna);
        reservas.add(reserva);
        return reserva;
    }

    public void quitar(Long reservaId) {
        boolean quitada = reservas.removeIf(r -> r.id().equals(reservaId));
        if (!quitada) {
            throw new ReservaNoEncontradaException(id, reservaId);
        }
    }

    public boolean estaCompleto() {
        return reservas.size() >= plazas;
    }

    public int plazasLibres() {
        return plazas - reservas.size();
    }

    public List<Reserva> getReservas() {
        return List.copyOf(reservas);
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Nivel getNivel() {
        return nivel;
    }

    public int getPlazas() {
        return plazas;
    }

    public double getPrecio() {
        return precio;
    }
}
