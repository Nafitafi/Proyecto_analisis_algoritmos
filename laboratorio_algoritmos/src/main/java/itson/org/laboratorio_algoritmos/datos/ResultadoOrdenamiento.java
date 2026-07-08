/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.org.laboratorio_algoritmos.datos;

/**
 * Record para poder transportar la información.
 * 
 * @author nafbr
 */
public record ResultadoOrdenamiento(
        String nombreAlgoritmo,
        long tiempoEjecucionNs, // Usando Nano segundos para mantener la precisión.
        long comparaciones,
        long intercambios
        ) {
}
