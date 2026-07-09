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
public class HeapSort implements AlgoritmoOrdenamiento {

    @Override
    public ResultadoOrdenamiento ordenar(int[] arregloOriginal) {
        int[] arr = arregloOriginal.clone();

        //Si el arreglo viene vacio 
        if (arr == null || arr.length <= 1) {
            return new ResultadoOrdenamiento("Heap Sort", 0, 0, 0);
        }

        long[] contadores = new long[2];

        long tiempoInicio = System.nanoTime();

        //Llamada al metodo del algoritmo
        heapSort(arr, contadores);
        //Calculo del tiempo
        long tiempoFinal = System.nanoTime();
        long tiempoTotal = tiempoFinal - tiempoInicio;

        // Extraemos los resultados
        long comparaciones = contadores[0];
        long intercambios = contadores[1];

        return new ResultadoOrdenamiento("Heap Sort", tiempoTotal, comparaciones, intercambios);
    }

    private static void heapSort(int[] arr, long[] contadores) {
        int n = arr.length;

        //1. Reorganizar el arreglo (Construir el Max-Heap)
        //Inicia desde el último nodo que tiene hijos hacia atrás
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i, contadores);
        }
        //2. Tomar uno a uno los elementos del heap

        for (int i = n - 1; i > 0; i--) {
            // Movemos la raíz actual (el máximo) al final del arreglo
            int temporal = arr[0];
            arr[0] = arr[i];
            arr[i] = temporal;

            contadores[1]++;

            heapify(arr, i, 0, contadores);
        }
    }

    //Método auxiliar para poder transformar un subárbol en un Max-Heap
    //n es el tamaño del heap actual, i es el índice del nodo ráiz del subárbol
    private static void heapify(int[] arr, int n, int i, long[] contadores) {
        int mayor = i; // Inicializamos el mayor como la raíz
        int izquierda = 2 * i + 1; // hijo izquierdo
        int derecha = 2 * i + 2;   // hijo derecho

        //Si el hijo izquierdo es mayor que la raíz
        if (izquierda < n) {
            contadores[0]++;
            if (arr[izquierda] > arr[mayor]) {
                mayor = izquierda;
            }
        }
        //Si el hijo derecho es más grande que el mayor hasta ahora
        if (derecha < n) {
            contadores[0]++;
            if (arr[derecha] > arr[mayor]) {
                mayor = derecha;
            }
        }

        //Si el mayor no es la raíz, se intercambia
        if (mayor != i) {
            int swap = arr[i];
            arr[i] = arr[mayor];
            arr[mayor] = swap;

            contadores[1]++;

            //De manera recursiva hacemos heapify en el subárbol afectado
            heapify(arr, n, mayor, contadores);
        }
    }

}
