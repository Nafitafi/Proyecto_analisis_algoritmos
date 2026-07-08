/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.org.laboratorio_algoritmos.ordenamientos;

import itson.org.laboratorio_algoritmos.datos.ResultadoOrdenamiento;

/**
 * Clase Insertion sort.
 *
 * @author Adrián
 */
public class InsertionSort implements AlgoritmoOrdenamiento {

    @Override
    public ResultadoOrdenamiento ordenar(int[] arregloOriginal) {
        // Se clona el arreglo para mantener la integridad del código
        int[] arr = arregloOriginal.clone();

        long comparaciones = 0;
        long intercambios = 0;

        //Se inicia el timer
        long tiempoInicio = System.nanoTime();

        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            int key = arr[i];
            int j = i - 1;

            //Contamos manualmente cada comparación
            while (j >= 0) {
                comparaciones++; //Se cuenta la comparación con el elemento que se considera "Key"

                if (arr[j] > key) {
                    arr[j + 1] = arr[j];
                    intercambios++; 
                    j--;
                } else {
                    break; // Si está en el lugar correcto, se sale del ciclo
                }
            }

            // Se acomoda la llave
            arr[j + 1] = key;
        }

        //Se detiene el timer
        long tiempoFinal = System.nanoTime();
        long tiempoTotal = tiempoFinal - tiempoInicio;

        // Se devuelven los resultados
        return new ResultadoOrdenamiento("Insertion Sort", tiempoTotal, comparaciones, intercambios);
    }

}
