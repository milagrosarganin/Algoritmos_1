package ar.uba.fi.cb100.clases.a2026.c02.tps.tp1;

import ar.uba.fi.cb100.librerias.excel.ExcelUtils;
import java.io.IOException;
import java.nio.file.Path;

public class ExportadorXLSX implements ExportadorDeReporte {

    @Override
    public void exportar(FilaDeSocio[] filas, Path destino) throws IOException {
        String[] encabezados = {"Padron", "Socio", "Prestamos", "DiasAtraso", "Multa", "Estado"};
        Object[][] datos = new Object[filas.length][];
        for (int i = 0; i < filas.length; i++) {
            FilaDeSocio f = filas[i];
            datos[i] = new Object[]{f.padron(), f.socio(), f.prestamos(), f.diasDeAtraso(), f.multa(), f.estado()};
        }
        ExcelUtils.escribir(destino, "Multas", encabezados, datos);
    }

    @Override
    public String extension() {
        return "xlsx";
    }
}