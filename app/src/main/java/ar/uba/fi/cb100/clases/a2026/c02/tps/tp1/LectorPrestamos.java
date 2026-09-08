package ar.uba.fi.cb100.clases.a2026.c02.tps.tp1;

import java.io.IOException;
import java.nio.file.Files; //Leer archivo entero
import java.nio.file.Path; //Tipo de dato para enviar la ruta del CSV
import java.time.LocalDate;
import java.time.format.DateTimeParseException; //Para detectar si el formato de la fecha esta bien
import java.util.Arrays;
import java.util.List; //
public class LectorPrestamos {

    public static ResultadoDeCarga cargar(Path archivo) throws IOException {
        RegistroPrestamos registro =  new RegistroSobreArreglo();
        var lineas = Files.readAllLines(archivo);
        int lineaDeDatos = 0;
        String[] errores = new String[lineas.size()];
        int nErrores = 0;
        for  (int i = 1; i <= lineas.size(); i++) { //i es el numero de linea
            String linea = lineas.get(i-1);
            int numeroDeLinea = i+1;
            if(!linea.startsWith("#") && !linea.isBlank()) {
                lineaDeDatos++;
                try {

                    try {
                        String[] lineInProcess = linea.split(";", -1); // De esta forma queda la linea separada en campos
                        if (lineInProcess.length != 6) {
                            throw new LineaInvalidaException(i, "se esperaban 6 o 5 campos y llegaron " + lineInProcess.length);
                        }
                        java.time.LocalDate fechaDeRetiro = java.time.LocalDate.parse(lineInProcess[0]);
                        int padron = Integer.parseInt(lineInProcess[1]);
                        String socio = lineInProcess[2];
                        String isbn = lineInProcess[3];
                        String titulo = lineInProcess[4];
                        java.time.LocalDate fechaDevolucion = null;

                        if (!(lineInProcess[5].isBlank())) {
                            fechaDevolucion = java.time.LocalDate.parse(lineInProcess[5]);
                        }

                        Prestamo prestamoEvaluado = new Prestamo(fechaDeRetiro, padron, socio, isbn, titulo, fechaDevolucion);
                        registro.registrar(prestamoEvaluado);
                    } catch (DateTimeParseException e) {
                        throw new LineaInvalidaException(numeroDeLinea, "fecha invalida");
                    } catch (NumberFormatException e) {
                        throw new LineaInvalidaException(numeroDeLinea, "padron no numerico");
                    } catch (IllegalArgumentException e) {
                        throw new LineaInvalidaException(numeroDeLinea, e.getMessage());
                    }
                } catch (LineaInvalidaException e) {
                    errores[nErrores] = "La linea "+e.numeroDeLinea()+": "+e.getMessage();
                    nErrores++;
                }
            }

        }
        return new ResultadoDeCarga(registro, Arrays.copyOf(errores, nErrores), lineaDeDatos);
    }

}