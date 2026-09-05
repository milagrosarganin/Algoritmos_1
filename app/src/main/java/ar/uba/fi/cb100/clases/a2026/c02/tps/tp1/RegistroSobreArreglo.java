package ar.uba.fi.cb100.clases.a2026.c02.tps.tp1;

public class RegistroSobreArreglo implements RegistroPrestamos{
    @Override
    public void registrar(Prestamo p) {

    }

    @Override
    public int cantidad() {
        return 0;
    }

    @Override
    public Prestamo obtener(int i) {
        return null;
    }

    @Override
    public int[] padrones() {
        return new int[0];
    }

    @Override
    public Prestamo[] prestamosDe(int padron) {
        return new Prestamo[0];
    }

    @Override
    public String[] titulosMasPedidos(int n) {
        return new String[0];
    }
}
