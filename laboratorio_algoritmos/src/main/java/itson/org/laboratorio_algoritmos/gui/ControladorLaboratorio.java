
package itson.org.laboratorio_algoritmos.gui;

import itson.org.laboratorio_algoritmos.datos.GeneradorDeArreglos;
import itson.org.laboratorio_algoritmos.datos.ResultadoOrdenamiento;
import itson.org.laboratorio_algoritmos.datos.TipoAlgoritmo;
import itson.org.laboratorio_algoritmos.ordenamientos.AlgoritmoOrdenamiento;
import itson.org.laboratorio_algoritmos.ordenamientos.BubbleSort;
import itson.org.laboratorio_algoritmos.ordenamientos.HeapSort;
import itson.org.laboratorio_algoritmos.ordenamientos.InsertionSort;
import itson.org.laboratorio_algoritmos.ordenamientos.MergeSort;
import itson.org.laboratorio_algoritmos.ordenamientos.QuickSort;
import itson.org.laboratorio_algoritmos.ordenamientos.SelectionSort;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase controlador.
 * Se encarga de orquestar la lógica entre la GUI y los algoritmos
 * @author nafbr
 */
public class ControladorLaboratorio {
    
    public List<ResultadoOrdenamiento> analizar(int tamano, GeneradorDeArreglos.TipoArreglo tipoArreglo,List<TipoAlgoritmo> algoritmosSeleccionados) {
        
        //Genración del arreglo
        int[] arregloBase = GeneradorDeArreglos.generar(tamano, tipoArreglo);
        
        // Preparación de la lista del resultado ordenado
        List<ResultadoOrdenamiento> resultados = new ArrayList<>();
        
        // Ejecución de cada algoritmo que se seleccionó en el checkbox
        for (TipoAlgoritmo tipo : algoritmosSeleccionados) {
            AlgoritmoOrdenamiento algoritmo = obtenerInstanciaAlgoritmo(tipo);
            
            // Ejecución y guardado de la información del Record.
            ResultadoOrdenamiento resultado = algoritmo.ordenar(arregloBase);
            resultados.add(resultado);
        }
        
        return resultados;
    }

    /**
     * Metodo auxiliar para convertir el enum del tipo de algoritmos
     * a su respectiva instancia concreta.
     */
    private AlgoritmoOrdenamiento obtenerInstanciaAlgoritmo(TipoAlgoritmo tipo) {
        switch (tipo) {
            case BUBBLE_SORT: return new BubbleSort();
            case SELECTION_SORT: return new SelectionSort();
            case INSERTION_SORT: return new InsertionSort();
            case MERGE_SORT: return new MergeSort();
            case QUICK_SORT: return new QuickSort();
            case HEAP_SORT: return new HeapSort();
            default: throw new IllegalArgumentException("Algoritmo no implementado: " + tipo);
        }
    }
}
