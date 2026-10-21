package com.dorcas.catalogo.tela;

public class Tela {
    private final Long id;
    private final String referencia;
    private String nombre;
    private Categoria categoria;
    private double precioMetro;
    private double stockMetros;
    private boolean aptaPrincipiantes;

    public Tela (Long id, String referencia, String nombre, Categoria categoria, double precioMetro, double stockMetros, boolean aptaPrincipiantes){
        this.id = id;
        this.referencia = referencia;
        this.nombre = nombre;
        this.precioMetro = precioMetro;
        this.stockMetros = stockMetros;
        this.aptaPrincipiantes = aptaPrincipiantes;
        this.categoria = categoria;
    }

    public Long getId() {
        return id;
    }

    public String getReferencia() {
        return referencia;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecioMetro() {
        return precioMetro;
    }

    public double getStockMetros() {
        return stockMetros;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public boolean esAptaPrincipiantes() {
        return aptaPrincipiantes;
    }

    @Override
    public String toString() {
        return "nombre='" + nombre + '\'' + " se usa para: " + categoria.getEtiqueta() + ", precioMetro=" + String.format("%.2f",precioMetro) +
                ", stockMetros=" + String.format("%.1f",stockMetros) +
                ", aptaPrincipiantes=" + aptaPrincipiantes;
    }

    public double valorEnStock(){
        return precioMetro * stockMetros;
    }


    public double precioPara(double metros){
        if (metros <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser positiva: " + metros);
        }
        double precio = precioMetro * metros;
        if (metros >= 5){
            precio = precio * 0.9;
        }
        return precio;
    }


    public boolean necesitaReponer(double minimo){
        if (stockMetros < minimo){
            return true;
        } else {
            return false;
        }
    }

    public void vender(double metros){
        if (metros <= 0) {
            throw new IllegalArgumentException("Los metros deben ser positivos: " + metros);
        }
        if (metros > stockMetros){
            throw new StockInsuficienteException(nombre, metros, stockMetros);
        }
        stockMetros -=metros;

    }

    public String consejoCuidado(){
        String consejo = switch (getCategoria()){
            case MODA -> "Lava a 30 °C y plancha del revés";
            case FIESTA -> "Lavado a mano, nunca en secadora";
            case INFANTIL -> "Lava antes de coser: puede encoger";
            case HOGAR -> "Admite lavado a 40 °C";
            case MANUALIDADES -> "No necesita cuidados especiales";
            case ABRIGO -> "Mejor en tintorería";
        };

        return consejo;
    }
}
