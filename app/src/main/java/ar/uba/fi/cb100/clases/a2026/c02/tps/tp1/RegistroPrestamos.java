package ar.uba.fi.cb100.clases.a2026.c02.tps.tp1;
//Esta interface no lleva importa porque las clases que se necesitan ya están en el paquete, por lo tanto pieden verse entre si
public interface RegistroPrestamos {

        void registrar(Prestamo p); //registra un objeto prestamo en donde se use el metodo
        int cantidad();
        Prestamo obtener(int i);              // i es n de prestamo q se quire obtener, IndexOutOfBoundsException si i es invalido
        int[] padrones();                     // devuelve un array con int de los padrones, sin repetidos, en orden de aparicion
        Prestamo[] prestamosDe(int padron);   // devulve un array con los prestamos que hizo el n de padron que se pasa comp parametro, arreglo vacio si no hay ninguno
        String[] titulosMasPedidos(int n);    // arreglo con los n titulos mas pedidos, desempate alfabetico
}
