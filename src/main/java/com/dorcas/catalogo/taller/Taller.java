package com.dorcas.catalogo.taller;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Taller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 80)
    private String nombre;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Nivel nivel;

    private int plazas;
    private double precio;

    @OneToMany(mappedBy = "taller", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reserva> reservas = new ArrayList<>();

    protected Taller(){}

    public Taller(String nombre, Nivel nivel, int plazas, double precio) {
        this.nombre = nombre;
        this.nivel = nivel;
        this.plazas = plazas;
        this.precio = precio;
    }

    public Reserva apuntar(String alumna) {
        if (estaCompleto()) {
            throw new TallerCompletoException(nombre, plazas);
        }
        boolean yaApuntada = reservas.stream()
                .anyMatch(r -> r.getAlumna().equalsIgnoreCase(alumna));
        if (yaApuntada) {
            throw new AlumnaYaApuntadaException(alumna, nombre);
        }
        Reserva reserva = new Reserva(this, alumna);
        reservas.add(reserva);
        return reserva;
    }

    public void quitar(Long reservaId) {
        boolean quitada = reservas.removeIf(r -> r.getId().equals(reservaId));
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
