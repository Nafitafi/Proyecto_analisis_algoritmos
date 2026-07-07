/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ordenamientos;

/**
 *
 * @author Adrián
 */
public class BubbleSort {

    
    //A el arreglo
    public static void bubbleSort(int[] A) {
        
        // n es el número de elementos del arreglo
        // cambio: El boolean que pasa a ser verdadero si ocurren cambios dentro de cada pasada 
        int n = A.length;
        boolean cambio;

        for (int i = 0; i <n-1; i++) {
            cambio = false;
            // Se ordena el último elemento de i
            for (int j = 0; j < n - i - 1; j++) {
                if (A[j] > A[j + 1]) {
                    // Se intercambia A[j] y A[j+1]
                    int temp = A[j];
                    A[j] = A[j + 1];
                    A[j + 1] = temp;
                    cambio = true;
                }
            }
            // Si no hubo intercambios en esta pasada, el arreglo ya está ordenado
            if (!cambio) {
                break;
            }
        }
    }

}
