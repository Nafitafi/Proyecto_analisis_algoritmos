package itson.org.laboratorio_algoritmos.datos;

/**
 * Record que representa un paso individual durante la animación de un
 * algoritmo de ordenamiento. Contiene los índices involucrados y el tipo
 * de operación realizada (comparación, intercambio o completado).
 * 
 * @author andres
 */
public record PasoAnimacion(
        int indiceA,
        int indiceB,
        TipoOperacion tipo
) {

    /**
     * Enum que define el tipo de operación en cada paso.
     */
    public enum TipoOperacion {
        COMPARACION,
        INTERCAMBIO,
        COMPLETADO
    }
}
