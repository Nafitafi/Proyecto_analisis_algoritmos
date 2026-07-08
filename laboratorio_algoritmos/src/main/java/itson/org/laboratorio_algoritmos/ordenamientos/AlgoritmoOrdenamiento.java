/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package itson.org.laboratorio_algoritmos.ordenamientos;

import itson.org.laboratorio_algoritmos.datos.ResultadoOrdenamiento;

/**
 * Interfaz de estrategia para los algoritmos de ordenamiento.
 * 
 * @author nafbr
 */
public interface AlgoritmoOrdenamiento {
    ResultadoOrdenamiento ordenar(int[] arreglo);
}
