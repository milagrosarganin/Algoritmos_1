package ar.uba.fi.cb100.clases.a2026.c02.tps.tp1;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;

public class Tp1 {
    private static final String ENTRADA_POR_DEFECTO = "datos/prestamos.csv"; //archivo a analizar
    private static final LocalDate CORTE_POR_DEFECTO = LocalDate.parse("2026-05-04"); //fecha de corte

    public static void main(String[] args) throws IOException {
        String rutaEntrada = args.length > 0 ? args[0] : ENTRADA_POR_DEFECTO;
        LocalDate corte = args.length > 1 ? LocalDate.parse(args[1]) : CORTE_POR_DEFECTO;

        ResultadoDeCarga resultado = LectorPrestamos.cargar(Path.of(rutaEntrada));

        System.out.println("Lineas de datos: " + resultado.lineasDeDatos() //printea esto
                + " | validas: " + resultado.registro().cantidad()
                + " | descartadas: " + resultado.errores().length);
        for (String error : resultado.errores()) {
            System.out.println(" " + error);
        }

        FilaDeSocio[] filas = Reporteador.porSocio(resultado.registro(), corte);
        String[] ranking = Reporteador.ranking(resultado.registro(), 3);

        ExportadorDeReporte exportadorTXT = new ExportadorTXT();
        exportadorTXT.exportar(filas, Path.of("reporte." + exportadorTXT.extension()));

        ExportadorDeReporte exportadorCSV = new ExportadorCSV();
        exportadorCSV.exportar(filas, Path.of("reporte." + exportadorCSV.extension()));

        System.out.println("TITULOS MAS PEDIDOS");
        for (int i = 0; i < ranking.length; i++) {
            System.out.println(" " + (i + 1) + ". " + ranking[i]);
        }
    }
}