package ar.uba.fi.cb100.clases.a2026.c02.tps.tp1;

import java.util.Arrays;

public class RegistroSobreArreglo implements RegistroPrestamos{

    private Prestamo[] datos;
    private int cantidad;

    @Override  //indica que voy a cambiar un metdo que ya existe en la interface
    public void registrar(Prestamo p) {

        if (cantidad == datos.length){

            datos = Arrays.copyOf(datos, datos.length * 2); //este metodo es de java, y puede hacer unnuevo array datos con el doble de capcidad del que tenia el anterior y copiando los obejtos que estaban en el anterior
        }

        datos [cantidad] = p;
        cantidad += 1;

    }
  @Override
    public int cantidad() {
        return cantidad;
    }

    @Override
    public Prestamo obtener(int i) {
        if (i >= cantidad){
            throw new IndexOutOfBoundsException("el numero de prestamo que desea ver no existe, ingrese otro numero");
        }
        else{
            return datos[i];
        }
    }

    @Override
    public int[] padrones() {
        int n = 0;
        var padronesList = new int[cantidad];
        for(int i=0; i < cantidad; i++){

            int padron = datos[i].padron();
            boolean yaEsta =false;
            for(int q=0; q < padronesList.length; q++){
                if(padron == padronesList[q]){
                    yaEsta = true;
                }
            }
            if (!yaEsta) {
                padronesList[n] = padron;
                n++;
            }
        }
        return Arrays.copyOf(padronesList, n);
    }

    @Override
    public Prestamo[] prestamosDe(int padron){
        Prestamo[] PrestamosHechos = new Prestamo[cantidad]; //array donde se guardara los prestamos hechos por el padron ingresado
        int posicion = 0;
        for(int i=0; i<cantidad; i++){

            if(datos[i].padron()==padron){

                PrestamosHechos[posicion] = datos[i];
                posicion++;
            }
        }
        return PrestamosHechos;
    }

    public int buscador(String[] titulos, String titulo, int limite){
        //este metodo devuelva la aposicion del titulo que se ingresa como parametro en el array que tmb se ingresa, si no esta devuelve -1
        for(int i =0; i<limite; i++){
            if(titulos[i].equals(titulo)){

                return i;
            }

            //
        };
        return -1;

    }

    public int maximador (int[] apariciones, int limite){
        //este metodo busca el maximo numero de un array y devuleve la posicion de ese n maximo
        int posicionDelValorMaximo = 1;
        for(int i=0; i<limite; i++){

            if (apariciones[i]>apariciones[posicionDelValorMaximo]){  //[1,33,6,7,8,9,10,4,50]
                posicionDelValorMaximo = i; //maximo actualiza el valor

            }
        }
        return posicionDelValorMaximo;
    }


    @Override
    public String[] titulosMasPedidos(int n) {

        var titulos = new String[cantidad];
        var apariciones = new int[cantidad];
        int limite = 0;
        var top = new String[cantidad];
        for(int i = 0; i<cantidad; i++){

            String titulo = datos[i].titulo();
            if(buscador(titulos, titulo, limite)!= -1){
                int posicion = buscador(titulos, titulo, limite);
                apariciones[posicion]++;
            }
            else{
                titulos[i] = titulo;
                apariciones[i] = 1;
                limite++;
            }
        }
        //hasta aca ya tengo los dos array de donde debo sacar el top n

        //este for es para buscar el maximo n de apariciones de un titulo y dsp ir eliminadolo  para encontrar el siguiente mayor n de apapriciones de un titulo y asi armar el top
        for(int i=0; i<=n; i++){

            int p = maximador(apariciones, limite); //posiicon del titulo mas grande
            top[i] = titulos[p]; //array devolucion
            apariciones[p] = apariciones[p+1];
            titulos[p] = titulos[p+1];
            n--;
        }

        return top;
    }


}