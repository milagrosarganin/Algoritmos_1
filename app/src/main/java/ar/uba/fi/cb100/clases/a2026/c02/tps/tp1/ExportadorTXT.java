package ar.uba.fi.cb100.clases.a2026.c02.tps.tp1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ExportadorTXT implements ExportadorDeReporte {

    @Override
    public void exportar(FilaDeSocio[] filas, Path destino) throws IOException {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("%-8s %-20s %-10s %-10s %-8s %-10s%n",
                "Padron", "Socio", "Prestamos", "DiasAtraso", "Multa", "Estado"));  //da los formatos a los encabezados
        sb.append("--------------------------------------------------------------------\n"); //separador que parece en el enuciado

        //variables para los totales
        int totalPrestamos = 0;
        int totalDiasAtraso = 0;
        int totalMulta = 0;

        for (int i = 0; i < filas.length; i++) {
            FilaDeSocio f = filas[i];
            sb.append(String.format("%-8d %-20s %-10d %-10d %-8d %-10s%n",
                    f.padron(), f.socio(), f.prestamos(), f.diasDeAtraso(), f.multa(), f.estado()));

            totalPrestamos += f.prestamos();
            totalDiasAtraso += f.diasDeAtraso();
            totalMulta += f.multa();
        }

        sb.append("--------------------------------------------------------------------\n");
        sb.append(String.format("TOTALES %d %d %d%n", totalPrestamos, totalDiasAtraso, totalMulta));

        Files.writeString(destino, sb.toString());
    }

    @Override
    public String extension() {
        return "TXT";
    }
}