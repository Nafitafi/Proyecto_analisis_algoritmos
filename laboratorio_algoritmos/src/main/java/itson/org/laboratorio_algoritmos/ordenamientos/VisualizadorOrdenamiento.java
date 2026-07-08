package itson.org.laboratorio_algoritmos.ordenamientos;

import itson.org.laboratorio_algoritmos.datos.PasoAnimacion;
import java.util.function.Consumer;

/**
 * Implementaciones de algoritmos de ordenamiento que reportan cada paso
 * (comparacion, intercambio, completado) a traves de un callback, para
 * ser usados en la animacion visual.
 * 
 * @author andres
 */
public class VisualizadorOrdenamiento {

    public static void bubbleSort(int[] arr, Consumer<PasoAnimacion> callback) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                callback.accept(new PasoAnimacion(j, j + 1, PasoAnimacion.TipoOperacion.COMPARACION));
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    callback.accept(new PasoAnimacion(j, j + 1, PasoAnimacion.TipoOperacion.INTERCAMBIO));
                }
            }
        }
        callback.accept(new PasoAnimacion(-1, -1, PasoAnimacion.TipoOperacion.COMPLETADO));
    }

    public static void selectionSort(int[] arr, Consumer<PasoAnimacion> callback) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                callback.accept(new PasoAnimacion(j, minIdx, PasoAnimacion.TipoOperacion.COMPARACION));
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }
            if (minIdx != i) {
                int temp = arr[i];
                arr[i] = arr[minIdx];
                arr[minIdx] = temp;
                callback.accept(new PasoAnimacion(i, minIdx, PasoAnimacion.TipoOperacion.INTERCAMBIO));
            }
        }
        callback.accept(new PasoAnimacion(-1, -1, PasoAnimacion.TipoOperacion.COMPLETADO));
    }

    public static void insertionSort(int[] arr, Consumer<PasoAnimacion> callback) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0) {
                callback.accept(new PasoAnimacion(j, j + 1, PasoAnimacion.TipoOperacion.COMPARACION));
                if (arr[j] > key) {
                    arr[j + 1] = arr[j];
                    callback.accept(new PasoAnimacion(j, j + 1, PasoAnimacion.TipoOperacion.INTERCAMBIO));
                    j--;
                } else {
                    break;
                }
            }
            arr[j + 1] = key;
        }
        callback.accept(new PasoAnimacion(-1, -1, PasoAnimacion.TipoOperacion.COMPLETADO));
    }
}
