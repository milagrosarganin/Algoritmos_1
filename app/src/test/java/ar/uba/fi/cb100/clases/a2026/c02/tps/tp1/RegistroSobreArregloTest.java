package ar.uba.fi.cb100.clases.a2026.c02.tps.tp1;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class RegistroSobreArregloTest {

    private Prestamo prestamo(int padron, String socio, String titulo) {
        return new Prestamo(LocalDate.parse("2026-03-01"), padron, socio, "123", titulo, null);
    }

    @Test
    void creceMasAlladeLaCapacidadInicial() {
        RegistroPrestamos r = new RegistroSobreArreglo();
        for (int i = 0; i < 20; i++) {
            r.registrar(prestamo(i + 1, "Socio" + i, "Titulo" + i));
        }
        assertEquals(20, r.cantidad());
    }

    @Test
    void padronesSinRepetidos() {
        RegistroPrestamos r = new RegistroSobreArreglo();
        r.registrar(prestamo(100, "Ana", "Libro A"));
        r.registrar(prestamo(100, "Ana", "Libro B"));
        r.registrar(prestamo(200, "Bruno", "Libro C"));
        int[] padrones = r.padrones();
        assertEquals(2, padrones.length);
        assertEquals(100, padrones[0]);
        assertEquals(200, padrones[1]);
    }

    @Test
    void prestamosDeUnPadronInexistenteDevuelveArregloVacio() {
        RegistroPrestamos r = new RegistroSobreArreglo();
        r.registrar(prestamo(100, "Ana", "Libro A"));
        assertEquals(0, r.prestamosDe(999).length);
    }

    @Test
    void desempateAlfabeticoEnElRanking() {
        RegistroPrestamos r = new RegistroSobreArreglo();
        r.registrar(prestamo(1, "Ana", "Clean Code"));
        r.registrar(prestamo(2, "Bruno", "Clean Code"));
        r.registrar(prestamo(3, "Carla", "Introduction to Algorithms"));
        r.registrar(prestamo(4, "Diego", "Introduction to Algorithms"));
        String[] top = r.titulosMasPedidos(2);
        assertEquals("Clean Code", top[0]);
        assertEquals("Introduction to Algorithms", top[1]);
    }

    @Test
    void obtenerConIndiceInvalidoLanzaExcepcion() {
        RegistroPrestamos r = new RegistroSobreArreglo();
        r.registrar(prestamo(1, "Ana", "Libro A"));
        assertThrows(IndexOutOfBoundsException.class, () -> r.obtener(5));
    }
}