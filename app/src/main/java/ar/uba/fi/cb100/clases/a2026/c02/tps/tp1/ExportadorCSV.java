package ar.uba.fi.cb100.clases.a2026.c02.tps.tp1;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;

public class ExportadorCSV implements ExportadorDeReporte{
    @Override
    public void exportar(FilaDeSocio[] filas, Path destino) throws IOException {
            StringBuilder sb = new StringBuilder(); //propio de java para construir lineas

            sb.append("padron;socio;prestamos;dias_atraso;multa;estado\n"); //agrega las columnas de salida del archivo

            for (int i = 0; i < filas.length; i++) {
                sb.append(filas[i].padron());
                sb.append(";");
                sb.append(filas[i].socio());
                sb.append(";");
                sb.append(filas[i].prestamos());
                sb.append(";");
                sb.append(filas[i].diasDeAtraso());
                sb.append(";");
                sb.append(filas[i].multa());
                sb.append(";");
                sb.append(filas[i].estado());
                sb.append(";");
                sb.append("\n");   // al final de cada fila, salto de línea
            }

            Files.writeString(destino, sb.toString());
        }

    @Override
    public String extension() {
        return "CSV";
    }
}


