package com.dorcas.catalogo.taller;

import jakarta.persistence.*;

@Entity
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "taller_id")
    private Taller taller;

    @Column(nullable = false, length = 80)
    private String alumna;

    protected Reserva(){}

    public Reserva(Taller taller, String alumna) {
        this.taller = taller;
        this.alumna = alumna;
    }

    public Long getId() {
        return id;
    }

    public Taller getTaller() {
        return taller;
    }

    public String getAlumna() {
        return alumna;
    }
}
