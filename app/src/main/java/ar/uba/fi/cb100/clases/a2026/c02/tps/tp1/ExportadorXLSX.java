package ar.uba.fi.cb100.clases.a2026.c02.tps.tp1;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;

public class ExportadorXLSX implements ExportadorDeReporte {

    @Override
    public void exportar(FilaDeSocio[] filas, Path destino) throws IOException {
        try (Workbook libro = new XSSFWorkbook()) {
            Sheet hoja = libro.createSheet("Multas");

            CellStyle negrita = libro.createCellStyle();
            Font fuente = libro.createFont();
            fuente.setBold(true);
            negrita.setFont(fuente);

            String[] encabezados = {"Padron", "Socio", "Prestamos", "DiasAtraso", "Multa", "Estado"};
            Row cabecera = hoja.createRow(0);
            for (int c = 0; c < encabezados.length; c++) {
                Cell celda = cabecera.createCell(c);
                celda.setCellValue(encabezados[c]);
                celda.setCellStyle(negrita);
            }

            for (int i = 0; i < filas.length; i++) {
                FilaDeSocio f = filas[i];
                Row fila = hoja.createRow(i + 1);
                fila.createCell(0).setCellValue(f.padron());
                fila.createCell(1).setCellValue(f.socio());
                fila.createCell(2).setCellValue(f.prestamos());
                fila.createCell(3).setCellValue(f.diasDeAtraso());
                fila.createCell(4).setCellValue(f.multa());
                fila.createCell(5).setCellValue(f.estado());
            }

            for (int c = 0; c < encabezados.length; c++) {
                hoja.autoSizeColumn(c);
            }

            try (FileOutputStream salida = new FileOutputStream(destino.toFile())) {
                libro.write(salida);
            }
        }
    }

    @Override
    public String extension() {
        return "xlsx";
    }
}