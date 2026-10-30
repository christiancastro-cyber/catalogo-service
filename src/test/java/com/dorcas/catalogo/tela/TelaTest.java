package com.dorcas.catalogo.tela;

import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class TelaTest {

    private Tela lame(){
        return new Tela("LAM-01", "Lamé", Categoria.FIESTA, 5.0,6.5,false);
    }

    @Test
    void venderDescuentaLosMetrosDelStock() {
            Tela tela = lame();
            tela.vender(2);
            assertThat(tela.getStockMetros()).isEqualTo(4.5);
    }

    @Test
    void venderMasDeLoQueHayLanzaStockInsuficiente(){
        Tela tela = lame();
        assertThatThrownBy(() -> tela.vender(20))
                .isInstanceOf(StockInsuficienteException.class)
                .hasMessageContaining("Lamé");
    }

    @Test
    void venderMetrosNegativosLanzaIllegalArgument() {
        Tela tela = lame();
        assertThatThrownBy(() -> tela.vender(-2))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void venderMasDeLoQueHayNoCambiaElStock() {
        Tela tela = lame();
        assertThatThrownBy(() -> tela.vender(20));
        assertThat(tela.getStockMetros()).isEqualTo(6.5);
    }

    @Test
    void aPartirDeCincoMetrosSeAplicaElDiezPorCiento() {
        Tela tela = lame();
        double precio = tela.precioPara(5);
        assertThat(precio).isCloseTo(22.5, within(0.001));
    }

}
