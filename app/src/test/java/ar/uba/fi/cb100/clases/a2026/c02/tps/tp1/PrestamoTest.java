package ar.uba.fi.cb100.clases.a2026.c02.tps.tp1;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class PrestamoTest {

    @Test
    void rechazaSocioVacio() {
        assertThrows(IllegalArgumentException.class, () ->
                new Prestamo(LocalDate.parse("2026-03-01"), 1, "", "123", "Titulo", null));
    }

    @Test
    void rechazaPadronNoPositivo() {
        assertThrows(IllegalArgumentException.class, () ->
                new Prestamo(LocalDate.parse("2026-03-01"), 0, "Ana", "123", "Titulo", null));
    }

    @Test
    void rechazaIsbnVacio() {
        assertThrows(IllegalArgumentException.class, () ->
                new Prestamo(LocalDate.parse("2026-03-01"), 1, "Ana", "", "Titulo", null));
    }

    @Test
    void rechazaTituloVacio() {
        assertThrows(IllegalArgumentException.class, () ->
                new Prestamo(LocalDate.parse("2026-03-01"), 1, "Ana", "123", "", null));
    }

    @Test
    void rechazaDevolucionAnteriorAlRetiro() {
        assertThrows(IllegalArgumentException.class, () ->
                new Prestamo(LocalDate.parse("2026-03-10"), 1, "Ana", "123", "Titulo",
                        LocalDate.parse("2026-03-01")));
    }

    @Test
    void calculaVencimientoCorrectamente() {
        Prestamo p = new Prestamo(LocalDate.parse("2026-03-01"), 1, "Ana", "123", "Titulo", null);
        assertEquals(LocalDate.parse("2026-03-15"), p.vencimiento());
    }

    @Test
    void atrasoEsCeroSiSeDevolvioAtiempo() {
        Prestamo p = new Prestamo(LocalDate.parse("2026-03-01"), 1, "Ana", "123", "Titulo",
                LocalDate.parse("2026-03-10"));
        assertEquals(0, p.diasDeAtraso(LocalDate.parse("2026-03-20")));
    }

    @Test
    void atrasoDePrestamoPendienteSeCuentaHastaElCorte() {
        Prestamo p = new Prestamo(LocalDate.parse("2026-03-01"), 1, "Ana", "123", "Titulo", null);
        // vencimiento: 2026-03-15, corte: 2026-03-20 -> 5 dias de atraso
        assertEquals(5, p.diasDeAtraso(LocalDate.parse("2026-03-20")));
    }

    @Test
    void multaTieneTopeDe3000() {
        Prestamo p = new Prestamo(LocalDate.parse("2026-01-01"), 1, "Ana", "123", "Titulo", null);
        // corte muy lejano -> atraso enorme -> deberia toparse en 3000
        assertEquals(3000, p.multa(LocalDate.parse("2026-06-01")));
    }
}