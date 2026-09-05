package ar.uba.fi.cb100.clases.a2026.c02.tps.tp1;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public record Prestamo(LocalDate fechaRetiro, int padron, String socio, String isbn, String titulo, LocalDate fechaDevolucion)


{

    public Prestamo {

        if (fechaDevolucion != null && fechaDevolucion.isBefore(fechaRetiro)) {
            throw new IllegalArgumentException("la devolucion no puede ser anterior al retiro");
        }

        if (padron <1){

            throw new IllegalArgumentException("El padron debe ser un N° mayor a cero");
        }

        if (socio == null || socio.isBlank()) {
            throw new IllegalArgumentException("No hay socio asignado");
        }

        if ( isbn == null || isbn.isBlank()) {
            throw new IllegalArgumentException("No hay ISBN asignado");
        }

        if (titulo == null || titulo.isBlank()){
            throw new IllegalArgumentException("No hay titulo asignado");
        }
    }

    public boolean estaPendiente(){

        //si la fechaDevolucion es nula queire decir que aun no se devolvio el libro, por lo tanto estaria pendiente la devolución
            return fechaDevolucion == null; // devuelve true por defecto
        }


    public LocalDate vencimiento(){

        return fechaRetiro.plusDays(14); // retonra la fecha de vencimiento contando desde la fecha que se retiro el libro
        }


    public long diasDeAtraso(LocalDate corte){

        long dias = ChronoUnit.DAYS.between(vencimiento(), corte); //cuenta cuantos dias hay desde el corte hasta el vencimiento
        return (int) Math.max(0, dias); //devuelve un entero que no es negativo
    }

    public int multa(LocalDate corte){

        int valor = 0;
        if (diasDeAtraso(corte)<21){
            valor = (int) (diasDeAtraso(corte)*150);
        }
        else {
            valor = 3000;
        }

        return valor;
    }


}


