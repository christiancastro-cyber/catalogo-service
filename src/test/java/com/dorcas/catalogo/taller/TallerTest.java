package com.dorcas.catalogo.taller;

import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class TallerTest {

    private Taller canastilla (){
        return  new Taller("Taller de Canastilla",Nivel.INICIACION,8,45.0);
    }

    private Taller canastillaPetit (){
        return  new Taller("Taller de Canastilla",Nivel.INICIACION,2,45.0);
    }

    @Test
    void apuntarUnaAlumnaReduceLasPlazasLibresEnUna() {
        Taller taller = canastilla();
        taller.apuntar("María");
        assertThat(taller.plazasLibres()).isEqualTo(7);
    }

    @Test
    void apuntarAlumnaYaApuntadaException(){
        Taller taller = canastillaPetit();

        taller.apuntar("Ana");

        assertThatThrownBy(() -> taller.apuntar("Ana"))
                .isInstanceOf(AlumnaYaApuntadaException.class);
    }

    @Test
    void tallerCompleto(){
        Taller taller = canastillaPetit();

        taller.apuntar("Ana");
        taller.apuntar("María");

        assertThatThrownBy(() -> taller.apuntar("Curiosa"))
                .isInstanceOf(TallerCompletoException.class);
    }



}
