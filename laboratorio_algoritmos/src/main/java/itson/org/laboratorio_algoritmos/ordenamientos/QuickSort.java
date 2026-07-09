/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.org.laboratorio_algoritmos.ordenamientos;

import itson.org.laboratorio_algoritmos.datos.ResultadoOrdenamiento;

/**
 *
 * @author Adrián
 */
public class QuickSort implements AlgoritmoOrdenamiento {

    @Override
    public ResultadoOrdenamiento ordenar(int[] arregloOriginal) {
        int[] arr = arregloOriginal.clone();

        //Si el arreglo esta vacio     
        if (arr == null || arr.length <= 1) {
            return new ResultadoOrdenamiento("Quick Sort", 0, 0, 0);
        }

        //Acumuladroes 
        //contadores[0] = comparaciones
        //contadores[1] = intercambios
        long[] contadores = new long[2];

        //Iniciar el timer
        long tiempoIncio = System.nanoTime();

        quickSort(arr, 0, arr.length - 1, contadores);

        //Detener timer
        long tiempoFinal = System.nanoTime();
        long tiempoTotal = tiempoFinal - tiempoIncio;

        long comparaciones = contadores[0];
        long intercambios = contadores[1];

        return new ResultadoOrdenamiento("Quick Sort", tiempoTotal, comparaciones, intercambios);

    }

    //Método recursivo que recibe los limites y los contadores
    private static void quickSort(int[] arr, int bajo, int alto, long[] contadores) {
        if (bajo < alto) {
            //indicePivote es el indice donde el pivote ya quedo en su pos correcta
            int indicePivote = particion(arr, bajo, alto, contadores);

            //Ordenar recursivamente los elementos antes y despues de la partición
            quickSort(arr, bajo, indicePivote - 1, contadores);
            quickSort(arr, indicePivote + 1, alto, contadores);
        }

    }

    private static int particion(int[] arr, int bajo, int alto, long[] contadores) {
        //Tomaremos el ultimo elemento como pivote (esto se puede cambiar
        int pivote = arr[alto];
        int i = (bajo - 1); //indice del elemento más pequeño

        for (int j = bajo; j < alto; j++) {
            contadores[0]++;

            if (arr[j] <= pivote) {
                i++;
                //Intercambio de arr[i] y arr[j]
                int temporal = arr[i];
                arr[i] = arr[j];
                arr[j] = temporal;

                contadores[1]++;
            }

        }

        //Intercambio del pivote con el elemento en la pos correcta
        int temporal = arr[i + 1];
        arr[i + 1] = arr[alto];
        arr[alto] = temporal;

        contadores[1]++;

        return i + 1;

    }
}
