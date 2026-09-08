package ar.uba.fi.cb100.clases.a2026.c02.tps.tp1;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.*;

class LectorPrestamosTest {

    @Test
    void archivoDePruebaTiene18ValidasY4Errores() throws IOException {
        ResultadoDeCarga resultado = LectorPrestamos.cargar(Path.of("datos/prestamos.csv"));
        assertEquals(18, resultado.registro().cantidad());
        assertEquals(4, resultado.errores().length);
    }
}