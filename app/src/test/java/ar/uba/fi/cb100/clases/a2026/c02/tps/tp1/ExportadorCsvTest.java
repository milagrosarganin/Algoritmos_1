package ar.uba.fi.cb100.clases.a2026.c02.tps.tp1;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.*;

class ExportadorCsvTest {

    @Test
    void escribeYLeeCorrectamente() throws IOException {
        FilaDeSocio[] filas = {
                new FilaDeSocio(39876, "Bruno Ferrari", 4, 24, 3600, "CON_DEUDA")
        };
        Path temp = Files.createTempFile("reporte", ".csv");
        new ExportadorCSV().exportar(filas, temp);

        var lineas = Files.readAllLines(temp);
        assertEquals("padron;socio;prestamos;dias_atraso;multa;estado", lineas.get(0));
        assertEquals("39876;Bruno Ferrari;4;24;3600;CON_DEUDA", lineas.get(1));
    }
}