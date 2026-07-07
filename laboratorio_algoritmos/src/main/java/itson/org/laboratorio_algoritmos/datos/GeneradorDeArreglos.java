/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.org.laboratorio_algoritmos.datos;

import java.util.Random;

/**
 *
 * @author marki
 */
public class GeneradorDeArreglos {
    //Limites de tamaño
    private static final int tamMin = 10;
    private static final int tamMax = 10000;
    
    //Rango de valores dentro del arreglo
    private static final int valorMinimo = 0;
    private static final int valorMaximo = 1000000;
    
    /**
     * Los 4 tipos de arreglo.
     */
    public enum TipoArreglo {
        ALEATORIO,
        ORDENADO,
        CASI_ORDENADO,
        INVERSO
    }
    
    /**
     * Aquí se llama desde la GUI, es el punto de partida.
     * Genera el arreglo en tamaño y tipo según sea pedido.
     * 
     * @param tamano cantidad de elementos que debe de estar entre 10 y 10,000
     * @param tipo es el tipo de arreglo seún sea deseado
     * @return arreglo de tipo entero ya ordenado segun el escenario
     */
    public static int[] generar(int tamano, TipoArreglo tipo){
        //Validacion de tamaño
        if(tamano < tamMin || tamano > tamMax){
            throw new IllegalArgumentException("El tamaño debe de estar entre " + tamMin + " y " + tamMax + ", actualmente se recibió: " + tamano);
        }
        switch(tipo){
            case ALEATORIO:
                return generarAleatorio(tamano);
            case ORDENADO:
                return generarOrdenado(tamano);
            case CASI_ORDENADO:
                return generarCasiOrdenado(tamano);
            case INVERSO:
                return generarInverso(tamano);
            default:
                throw new IllegalArgumentException("Tipo de arreglo no disponible");
        }
    }
    
     /**
     * Devuelve un solo valor entero aleatorio dentro del rango permitido
     * (VALOR_MIN a VALOR_MAX). Se declara en método aquí para no repetir la
     * misma fórmula en cada método.
     */
    private static int valorAleatorio(Random random) {
        return valorMinimo + random.nextInt(valorMaximo - valorMinimo + 1);
    }
    
    /**
     * Genera un arreglo aleatorio, recorre el arreglo y simplemente pone un numero random dentro de cada posición
     */
    private static int[] generarAleatorio(int tamano){
        Random random = new Random();
        int[] arreglo = new int[tamano];
        
        for(int i = 0; i < tamano; i++){
            arreglo[i] = valorAleatorio(random);
        }
        return arreglo;
    }
    
    /**
     * Genera un arreglo ordenado de menor a mayot
     * 
     * Aqui se generan valores aleatorios y despues se usa Arrays.sort para ordenarlo jeje
     */
    private static int[] generarOrdenado(int tamano){
        int[] arreglo = generarAleatorio(tamano);
        java.util.Arrays.sort(arreglo);
        return arreglo;
    }
    
    /**
     * Genera un arreglo ordenado de mayor a menor (inverso)
     * 
     * Se utiliza un arreglo generado de forma ascendente y lugo se invierte cambiando los elementos
     */
    private static int[] generarInverso(int tamano) {
        int[] arreglo = generarOrdenado(tamano);
 
        int inicio = 0;
        int fin = arreglo.length - 1;
        while (inicio < fin) {
            int temp = arreglo[inicio];
            arreglo[inicio] = arreglo[fin];
            arreglo[fin] = temp;
            inicio++;
            fin--;
        }
        return arreglo;
    }
    
    /**
     * Genera un arreglo "casi ordenado" utilizando un porcentaje de elementos fuera de lugar
     * 
     * Funciona de la siguiente manera:
     * 1. Se parte un arreglo ya ordenado
     * 2. Se calcula cuantos cambios representan 5% del arreglo que es lo que determina cuantas posiciones se intercambiaran 
     * 3. Se eligen posiciones al azar y se intercambian entre sí
     */
     private static int[] generarCasiOrdenado(int tamano) {
        int[] arreglo = generarOrdenado(tamano);
        Random random = new Random();
        int numIntercambios = (int) Math.ceil(tamano * 0.05);
 
        for (int k = 0; k < numIntercambios; k++) {
            int i = random.nextInt(tamano);
            int j = random.nextInt(tamano);
            int temp = arreglo[i];
            arreglo[i] = arreglo[j];
            arreglo[j] = temp;
        }
 
        return arreglo;
    }
}
