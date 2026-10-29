package com.dorcas.catalogo.tela;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TelaService {

    private final TelaRepository telaRepository;
    private final VentaRepository ventaRepository;

    public TelaService(TelaRepository telaRepository, VentaRepository ventaRepository) {

        this.telaRepository = telaRepository;
        this.ventaRepository = ventaRepository;

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
        if (ventaRepository.existsByTelaId(id)){
            throw new TelaConVentasException(id);
        }
        telaRepository.delete(tela);
    }

    @Transactional
    public VentaOutputDto vender(Long id, double metros){
        Tela tela = buscarPorId(id);
        tela.vender(metros);
        telaRepository.save(tela);
        double importe = tela.precioPara(metros);
        Venta venta = ventaRepository.save(new Venta(tela, metros, importe, tela.getStockMetros()));
        return VentaOutputDto.desde(venta);
    }

    public List<VentaOutputDto> listarVentas(Long telaId) {
        buscarPorId(telaId);
        return ventaRepository.findByTelaIdOrderByIdAsc(telaId).stream()
                .map(VentaOutputDto::desde)
                .toList();
    }

    public List<Tela> listaDeReposicion(double minimo){
        return telaRepository.findByStockMetrosLessThanOrderByStockMetros(minimo);
    }

    public TotalVentasOutputDto totalVentas(Long telaId){
        buscarPorId(telaId);
        double metros = ventaRepository.sumarMetrosVendidos(telaId);
        return new TotalVentasOutputDto(telaId, metros)
;    }

}
