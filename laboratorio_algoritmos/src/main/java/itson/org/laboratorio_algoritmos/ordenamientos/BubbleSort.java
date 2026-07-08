/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.org.laboratorio_algoritmos.ordenamientos;

import itson.org.laboratorio_algoritmos.datos.ResultadoOrdenamiento;

/**
 * Clase bubble sort.
 * @author Adrián
 */
public class BubbleSort implements AlgoritmoOrdenamiento {

    @Override
    public ResultadoOrdenamiento ordenar(int[] arregloOriginal) {
        // Por buena practica, clonamos el arreglo para mantener la integridad del original
        int[] arr = arregloOriginal.clone();

        long comparaciones = 0;
        long intercambios = 0;

        // Se inicia el timer
        long tiempoInicio = System.nanoTime();

        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                comparaciones++; // Contamos cada comparación

                if (arr[j] > arr[j + 1]) {
                    // Se ejecuta el swap
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;

                    intercambios++; // Contamos el swap
                }
            }
        }

        // Detenemos el timer
        long tiempoFinal = System.nanoTime();
        long tiempoTotal = tiempoFinal - tiempoInicio;

        // Se devuelve el resultado en un record
        return new ResultadoOrdenamiento("Bubble Sort", tiempoTotal, comparaciones, intercambios);
    }

}
