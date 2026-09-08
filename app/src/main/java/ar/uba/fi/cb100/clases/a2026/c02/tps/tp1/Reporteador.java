package ar.uba.fi.cb100.clases.a2026.c02.tps.tp1;

import java.time.LocalDate;
public class Reporteador {
    public static FilaDeSocio[] porSocio(RegistroPrestamos r, LocalDate corte) {

        int[] padrones = r.padrones();
        FilaDeSocio[] socio = new FilaDeSocio[padrones.length];

        for (int i = 0; i < padrones.length; i++) {
            //r.obtener(i) es un prestamo
            // obj: armar una fila con cada socio y hay que agrupar los prestamos
            int cantidadPrestamos = 1;
            String estado;
            int padron = 0;
            String nombre = "";
            int diasDeAtraso = 0;
            int multa = 0;
            for (int q = 0; q < r.cantidad(); q++) {
                if (padrones[i] == r.obtener(q).padron() && i != q) {
                    padron = r.obtener(q).padron();
                    nombre = r.obtener(q).socio();
                    diasDeAtraso = Math.toIntExact(r.obtener(q).diasDeAtraso(corte));
                    multa = r.obtener(q).multa(corte);

                    cantidadPrestamos++;
                    multa += r.obtener(q).multa(corte);
                    diasDeAtraso = Math.toIntExact(r.obtener(q).diasDeAtraso(corte));
                }

                if (multa > 0) {
                    estado = "CONDEUDA";
                } else {
                    estado = "ALDIA";
                }
                //String[] lineaAGuardar = padron + nombre + cantidadPrestamos + diasDeAtraso + multa + estado
                socio[i] = new FilaDeSocio(padron, nombre, cantidadPrestamos, diasDeAtraso, multa, estado);
            }

        }

        for (int i = 0; i < socio.length - 1; i++) {
            for (int j = 0; j < socio.length - 1 - i; j++) {
                boolean debeIntercambiar =
                        socio[j].multa() < socio[j + 1].multa() ||
                                (socio[j].multa() == socio[j + 1].multa() &&
                                        socio[j].socio().compareTo(socio[j + 1].socio()) > 0);

                if (debeIntercambiar) {
                    FilaDeSocio temp = socio[j];
                    socio[j] = socio[j + 1];
                    socio[j + 1] = temp;
                }


            }
        }

        return socio;
    }


    public static String[] ranking(RegistroPrestamos r, int n) {
        return r.titulosMasPedidos(n);
    }

}

