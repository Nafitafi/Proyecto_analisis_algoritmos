
package itson.org.laboratorio_algoritmos.ordenamientos;

import itson.org.laboratorio_algoritmos.datos.ResultadoOrdenamiento;

/**
 * Clase Selection Sort.
 *
 * @author Adrián
 */
public class SelectionSort implements AlgoritmoOrdenamiento {

    @Override
    public ResultadoOrdenamiento ordenar(int[] arregloOriginal) {
        //Clonamos el arreglo original para manteber la integridad de los datos.
        int[] arr = arregloOriginal.clone();

        long comparaciones = 0;
        long intercambios = 0;

        // Se inicia el timer
        long startTime = System.nanoTime();

        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < n; j++) {
                comparaciones++; // Se cuentan las comparaciones
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            //Solo se hace swap con elementos menores
            if (minIndex != i) {
                int temp = arr[minIndex];
                arr[minIndex] = arr[i];
                arr[i] = temp;

                intercambios++; //Se contabiliza el swap
            }
        }

        // El timer para
        long endTime = System.nanoTime();
        long tiempoTotal = endTime - startTime;

        // Se muestran los resultados con el record
        return new ResultadoOrdenamiento("Selection Sort", tiempoTotal, comparaciones, intercambios);
    }

}
