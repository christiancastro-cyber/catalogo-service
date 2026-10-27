package com.dorcas.catalogo.tela;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TelaService {

    private final TelaRepository telaRepository;
    private final List<VentaOutputDto> ventas = new ArrayList<>();
    private long siguienteIdVenta = 1;

    public TelaService(TelaRepository telaRepository) {
        this.telaRepository = telaRepository;
    }

    public List<Tela> listar(Categoria categoria, Boolean aptaPrincipiantes) {
        return telaRepository.buscar(categoria, aptaPrincipiantes);
    }

    public Tela buscarPorId(Long id) {
        return telaRepository.findById(id)
                .orElseThrow(() -> new TelaNoEncontradaException(id));
    }


    public Tela crear(TelaInputDto input) {
        if(telaRepository.existsByReferenciaIgnoreCase(input.referencia())){
            throw new ReferenciaDuplicadaException(input.referencia());
        }
        Tela tela = new Tela(input.referencia(), input.nombre(), input.categoria(),
                input.precioMetro(), input.stockMetros(), input.aptaPrincipiantes());
        return telaRepository.save(tela);
    }

    public Tela modificar(Long id, TelaInputDto input) {
        Tela tela = buscarPorId(id);
        if(telaRepository.existsByReferenciaIgnoreCaseAndIdNot(input.referencia(), id)){
            throw new ReferenciaDuplicadaException(input.referencia());
        }
        tela.modificar(input.referencia(), input.nombre(), input.categoria(),
                input.precioMetro(), input.stockMetros(), input.aptaPrincipiantes());
        return telaRepository.save(tela);
    }

    public Tela cambiarPrecio(Long id, double nuevoPrecio) {
        Tela tela = buscarPorId(id);
        tela.cambiarPrecio(nuevoPrecio);
        return telaRepository.save(tela);
    }

    public void borrar(Long id) {
        Tela tela = buscarPorId(id);
        telaRepository.delete(tela);
    }

    public VentaOutputDto vender(Long id, double metros) {
        Tela tela = buscarPorId(id);
        tela.vender(metros);
        telaRepository.save(tela);

        double importe = tela.precioPara(metros);
        VentaOutputDto venta = new VentaOutputDto(siguienteIdVenta++, tela.getId(),
                metros, importe, tela.getStockMetros());
        ventas.add(venta);
        return venta;
    }

    public List<VentaOutputDto> listarVentas(Long telaId) {
        buscarPorId(telaId);
        return ventas.stream()
                .filter(v -> v.telaId().equals(telaId))
                .toList();
    }

    public List<Tela> listaDeReposicon(double minimo){
        return telaRepository.findByStockMetrosLessThanOrderByStockMetros(minimo);
    }

}
