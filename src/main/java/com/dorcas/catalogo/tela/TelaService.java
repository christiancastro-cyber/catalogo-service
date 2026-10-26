package com.dorcas.catalogo.tela;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TelaService {
    private final List<Tela> telas = new ArrayList<>();
    private final List<VentaOutputDto> ventas = new ArrayList<>();
    private long siguienteIdTela = 9;
    private long siguienteIdVenta = 1;

    public TelaService(){
        telas.add(new Tela(1L,"ALG-01","Algodón",Categoria.INFANTIL ,8.50, 15.0, true));
        telas.add(new Tela(2L, "LON-01","Loneta Lisa", Categoria.HOGAR,11.0, 6, true));
        telas.add(new Tela(3L,"LON-02","Loneta estampada",Categoria.HOGAR, 9.30, 5, true));
        telas.add(new Tela(4L, "LY-01","Lycra", Categoria.MODA,8.50, 15.5, false));
        telas.add(new Tela(5L, "STR-01","Stretch", Categoria.MODA,4.5, 9, true));
        telas.add(new Tela(6L,"ANT-01","Antelina", Categoria.HOGAR, 6.0, 12.5, false));
        telas.add(new Tela (7L,"LAM-01","Lamé", Categoria.FIESTA,5.0, 6.5, false));
        telas.add(new Tela(8L, "COR-01","Coralina", Categoria.ABRIGO, 11.50, 8, false));
    }

    public Tela crear(TelaInputDto input){
        comprobarReferenciaLibre(input.referencia(), null);
        Tela tela = new Tela(siguienteIdTela++, input.referencia(), input.nombre(), input.categoria(),
                            input.precioMetro(), input.stockMetros(), input.aptaPrincipiantes());
        telas.add(tela);
        return tela;
    }

    public Tela modificar(Long id, TelaInputDto input){
        Tela tela = buscarPorId(id);
        comprobarReferenciaLibre(input.referencia(), id);
        tela.modificar(input.referencia(), input.nombre(), input.categoria(), input.precioMetro(), input.stockMetros(), input.aptaPrincipiantes());
        return tela;
    }

    public Tela cambiarPrecio(Long id, double nuevoPrecio){
        Tela tela = buscarPorId(id);
        tela.cambiarPrecio(nuevoPrecio);
        return tela;
    }

    public void borrar(Long id){
        Tela tela = buscarPorId(id);
        telas.remove(tela);
    }

    public VentaOutputDto vender(Long id, double metros){
        Tela tela = buscarPorId(id);
        tela.vender(metros);
        double importe = tela.precioPara(metros);
        VentaOutputDto venta = new VentaOutputDto (siguienteIdVenta++, tela.getId(), metros,importe,tela.getStockMetros());
        ventas.add(venta);
        return venta;
    }


    public List<Tela> listar(Categoria categoria, Boolean aptaPrincipiantes){
        return telas.stream()
                .filter(t -> categoria == null || t.getCategoria() == categoria)
                .filter( t -> aptaPrincipiantes == null || t.esAptaPrincipiantes() == aptaPrincipiantes)
                .toList();
    }

    public List<VentaOutputDto> listarVentas(Long telaId){
        buscarPorId(telaId);
        return ventas.stream()
                .filter(v -> v.telaId().equals(telaId))
                .toList();
    }

    public Tela buscarPorId(Long id){
        return telas.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new TelaNoEncontradaException(id));
    }



    private void comprobarReferenciaLibre(String referencia, Long idPropio){
        boolean ocupada = telas.stream()
                .anyMatch(t -> t.getReferencia().equalsIgnoreCase(referencia) && !t.getId().equals(idPropio));
        if(ocupada){
            throw new ReferenciaDuplicadaException(referencia);
        }
    }




}
