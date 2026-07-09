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

    /**
     * Implementación de Merge Sort con reporte de pasos para animación.
     * Divide el arreglo en mitades, las ordena recursivamente y las fusiona.
     */
    public static void mergeSort(int[] arr, Consumer<PasoAnimacion> callback) {
        if (arr.length <= 1) {
            callback.accept(new PasoAnimacion(-1, -1, PasoAnimacion.TipoOperacion.COMPLETADO));
            return;
        }
        int[] temporal = new int[arr.length];
        mergeSortRec(arr, temporal, 0, arr.length - 1, callback);
        callback.accept(new PasoAnimacion(-1, -1, PasoAnimacion.TipoOperacion.COMPLETADO));
    }

    private static void mergeSortRec(int[] arr, int[] temporal, int izquierda, int derecha, Consumer<PasoAnimacion> callback) {
        if (izquierda < derecha) {
            int mitad = (izquierda + derecha) / 2;
            mergeSortRec(arr, temporal, izquierda, mitad, callback);
            mergeSortRec(arr, temporal, mitad + 1, derecha, callback);
            merge(arr, temporal, izquierda, mitad, derecha, callback);
        }
    }

    private static void merge(int[] arr, int[] temporal, int izquierda, int mitad, int derecha, Consumer<PasoAnimacion> callback) {
        for (int i = izquierda; i <= derecha; i++) {
            temporal[i] = arr[i];
        }

        int i = izquierda;
        int j = mitad + 1;
        int k = izquierda;

        while (i <= mitad && j <= derecha) {
            callback.accept(new PasoAnimacion(i, j, PasoAnimacion.TipoOperacion.COMPARACION));
            if (temporal[i] <= temporal[j]) {
                arr[k] = temporal[i];
                i++;
            } else {
                arr[k] = temporal[j];
                j++;
            }
            callback.accept(new PasoAnimacion(k, -1, PasoAnimacion.TipoOperacion.INTERCAMBIO));
            k++;
        }

        while (i <= mitad) {
            arr[k] = temporal[i];
            callback.accept(new PasoAnimacion(k, -1, PasoAnimacion.TipoOperacion.INTERCAMBIO));
            i++;
            k++;
        }
    }

    /**
     * Implementación de Quick Sort con reporte de pasos para animación.
     * Usa el último elemento como pivote y particiona el arreglo.
     */
    public static void quickSort(int[] arr, Consumer<PasoAnimacion> callback) {
        if (arr.length <= 1) {
            callback.accept(new PasoAnimacion(-1, -1, PasoAnimacion.TipoOperacion.COMPLETADO));
            return;
        }
        quickSortRec(arr, 0, arr.length - 1, callback);
        callback.accept(new PasoAnimacion(-1, -1, PasoAnimacion.TipoOperacion.COMPLETADO));
    }

    private static void quickSortRec(int[] arr, int bajo, int alto, Consumer<PasoAnimacion> callback) {
        if (bajo < alto) {
            int indicePivote = particion(arr, bajo, alto, callback);
            quickSortRec(arr, bajo, indicePivote - 1, callback);
            quickSortRec(arr, indicePivote + 1, alto, callback);
        }
    }

    private static int particion(int[] arr, int bajo, int alto, Consumer<PasoAnimacion> callback) {
        int pivote = arr[alto];
        int i = bajo - 1;

        for (int j = bajo; j < alto; j++) {
            callback.accept(new PasoAnimacion(j, alto, PasoAnimacion.TipoOperacion.COMPARACION));
            if (arr[j] <= pivote) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                callback.accept(new PasoAnimacion(i, j, PasoAnimacion.TipoOperacion.INTERCAMBIO));
            }
        }

        int temp = arr[i + 1];
        arr[i + 1] = arr[alto];
        arr[alto] = temp;
        callback.accept(new PasoAnimacion(i + 1, alto, PasoAnimacion.TipoOperacion.INTERCAMBIO));

        return i + 1;
    }

    /**
     * Implementación de Heap Sort con reporte de pasos para animación.
     * Construye un Max-Heap y extrae la raíz iterativamente.
     */
    public static void heapSort(int[] arr, Consumer<PasoAnimacion> callback) {
        int n = arr.length;
        if (n <= 1) {
            callback.accept(new PasoAnimacion(-1, -1, PasoAnimacion.TipoOperacion.COMPLETADO));
            return;
        }

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i, callback);
        }

        for (int i = n - 1; i > 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            callback.accept(new PasoAnimacion(0, i, PasoAnimacion.TipoOperacion.INTERCAMBIO));

            heapify(arr, i, 0, callback);
        }
        callback.accept(new PasoAnimacion(-1, -1, PasoAnimacion.TipoOperacion.COMPLETADO));
    }

    private static void heapify(int[] arr, int n, int i, Consumer<PasoAnimacion> callback) {
        int mayor = i;
        int izquierda = 2 * i + 1;
        int derecha = 2 * i + 2;

        if (izquierda < n) {
            callback.accept(new PasoAnimacion(izquierda, mayor, PasoAnimacion.TipoOperacion.COMPARACION));
            if (arr[izquierda] > arr[mayor]) {
                mayor = izquierda;
            }
        }

        if (derecha < n) {
            callback.accept(new PasoAnimacion(derecha, mayor, PasoAnimacion.TipoOperacion.COMPARACION));
            if (arr[derecha] > arr[mayor]) {
                mayor = derecha;
            }
        }

        if (mayor != i) {
            int temp = arr[i];
            arr[i] = arr[mayor];
            arr[mayor] = temp;
            callback.accept(new PasoAnimacion(i, mayor, PasoAnimacion.TipoOperacion.INTERCAMBIO));

            heapify(arr, n, mayor, callback);
        }
    }
}
