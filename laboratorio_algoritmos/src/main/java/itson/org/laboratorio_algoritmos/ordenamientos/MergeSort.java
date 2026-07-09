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
public class MergeSort implements AlgoritmoOrdenamiento {

    @Override
    public ResultadoOrdenamiento ordenar(int[] arregloOriginal) {
        int[] arr = arregloOriginal.clone();

        long tiempoInicio = System.nanoTime();
        //Si el arreglo viene vacio
        if (arr == null || arr.length <= 1) {
            return new ResultadoOrdenamiento("Merge Sort", 0, 0, 0);
        }
        
        /**
         * Como merge sort usa recursión y metodos axuliares, tendremos que pasar como
         * referencia los acumuladores para evitar perderlos en el proceso
         */
        // contadores[0] = comparaciones
        // contadores[1] = intercambios 
        long[] contadores = new long[2];
        
        int[] temporal = new int[arr.length]; // Arreglo temporal para la fusión
        mergeSort(arr, temporal, 0, arr.length - 1, contadores);
        
        // Detenemos el timer
        long tiempoFinal = System.nanoTime();
        long tiempoTotal = tiempoFinal - tiempoInicio;
        //Extraer los acumuladores del arreglo 
        long comparaciones = contadores[0];
        long intercambios = contadores[1];

        return new ResultadoOrdenamiento("Merge Sort", tiempoTotal, comparaciones, intercambios);

    }

    private static void mergeSort(int[] arr, int[] temporal, int izquierda, int derecha,long[] contadores) {
        if (izquierda < derecha) {
            int mitad = (izquierda + derecha) / 2;

            // Ordenar la primera y segunda mitad
            mergeSort(arr, temporal, izquierda, mitad, contadores);
            mergeSort(arr, temporal, mitad + 1, derecha, contadores);

            // Fusionar las mitades ordenadas
            merge(arr, temporal, izquierda, mitad, derecha, contadores);
        }
    }

    private static void merge(int[] arr, int[] temporal, int izquierda, int mitad, int derecha, long[] contadores) {
        // Copiar los elementos al arreglo temporal
        for (int i = izquierda; i <= derecha; i++) {
            temporal[i] = arr[i];
        }

        int i = izquierda;     // Puntero para la primera mitad
        int j = mitad + 1;     // Puntero para la segunda mitad
        int k = izquierda;     // Puntero para escribir en el arreglo original

        // Fusionar los subarreglos en orden
        while (i <= mitad && j <= derecha) {
            contadores[0]++;
            if (temporal[i] <= temporal[j]) {
                arr[k] = temporal[i];
                i++;
            } else {
                arr[k] = temporal[j];
                j++;
            }
            contadores[1]++;
            k++;
        }

        // Copiar los elementos restantes de la primera mitad si los hay
        while (i <= mitad) {
            arr[k] = temporal[i];
            contadores[1]++;
            i++;
            k++;
        }
    }
}
