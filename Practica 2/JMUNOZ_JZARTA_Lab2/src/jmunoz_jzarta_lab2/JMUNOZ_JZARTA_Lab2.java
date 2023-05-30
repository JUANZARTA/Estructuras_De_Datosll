
package jmunoz_jzarta_lab2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//Lab2 EDD2 Prof. Juan Pablo Martinez P.
// Entregado por: James Muñoz Soscue - Juan Camilo Zarta Campo
public class JMUNOZ_JZARTA_Lab2 {

    static float matriz[][] = new float[2][50];
    static float matriz_organizada[][] = new float[3][50];
    static float solucion[][] = new float[2][30];

    public static void main(String[] args) {
        int cant_trabajos;
        int cant_tiempo;
        int cant_casos;

        File f = new File("prueba_jmartinez_lab2.txt");
        Scanner leer = new Scanner(System.in);

        System.out.print("Ingrese la cantidad de casos a validar: ");
        cant_casos = leer.nextInt();

        try ( Scanner entrada = new Scanner(f)) {

            for (int i = 0; i < cant_casos; i++) {
                cant_trabajos = entrada.nextInt(); //se lee un entero del archivo
                System.out.println("cant_trabajos: " + cant_trabajos);
                cant_tiempo = entrada.nextInt();
                System.out.println("cant_tiempo: " + cant_tiempo);

                for (int j = 0; j < 2; j++) {
                    for (int k = 0; k < cant_trabajos; k++) {
                        matriz[j][k] = entrada.nextInt();
                        System.out.print(matriz[j][k] + " ");  //imprime lo que se va almacenando en la matriz
                    }
                    System.out.println();
                }

                Organizar_por_beneficio(cant_trabajos);

                Solucion_Voraz(cant_trabajos, cant_tiempo, 0, 0);

            }//cierro el for principal

        } catch (FileNotFoundException e) {
            System.out.println(e.toString());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    private static void Organizar_por_beneficio(int cant_trabajos) {
        float mayor_beneficio = 9000;
        float beneficio_temp = 0;
        float beneficio;
        int pos_trabajo = 0;
        int i = 0;;

        for (int j = 0; j < cant_trabajos; j++) {
            beneficio_temp = 0;
            for (int k = 0; k < cant_trabajos; k++) {
                beneficio = matriz[0][k] / matriz[1][k];

                if (beneficio_temp < beneficio && beneficio < mayor_beneficio) {
                    beneficio_temp = beneficio;
                    pos_trabajo = k;
                }

            }
            mayor_beneficio = beneficio_temp;

            matriz_organizada[0][i] = matriz[0][pos_trabajo];
            matriz_organizada[1][i] = matriz[1][pos_trabajo];
            matriz_organizada[2][i] = pos_trabajo;

            i++;

        }
        System.out.print("matriz organizada.\n");
        for (int j = 0; j <= 2; j++) {
            for (int k = 0; k < cant_trabajos; k++) {
                System.out.print(matriz_organizada[j][k] + " ");  //imprime la matriz
            }
            System.out.println();
        }
    }

    private static boolean Solucion_Voraz(int cant_trabajos, float cant_tiempo, int pos_anterior, int pos_solucion) {
        for (int k = pos_anterior; k < cant_trabajos; k++) {
            int cont = 0;
            while (cant_tiempo >= matriz_organizada[1][k] && cant_tiempo >= 0) {
                cant_tiempo = cant_tiempo - matriz_organizada[1][k];
                pos_anterior = k;
                cont++;
            }
            if (cont != 0) {
                solucion[0][pos_solucion] = matriz_organizada[2][k];
                solucion[1][pos_solucion] = cont;
                pos_solucion++;

                // System.out.print("necesitaremos " + cont + " de " + matriz_organizada[2][k]);
            }
            if (cont == 0 && pos_solucion == 0 && pos_anterior == 0) {
                System.out.println();
                System.out.println("no se encontro solucion");
                System.out.println();

                return false;
            }

        }
        System.out.println();

        if (cant_tiempo != 0 && cant_trabajos >= pos_anterior + 1) {
            cant_tiempo += matriz_organizada[1][pos_anterior];
            //   if (pos_solucion != 0) {
            reducir_solucion_anterior(pos_solucion);
            //    } else {
            //      System.out.println("no se encontro solucion");
            //      return false;
            // }

            return Solucion_Voraz(cant_trabajos, cant_tiempo, pos_anterior + 1, pos_solucion);
        }

        if (cant_trabajos < pos_anterior + 1 || (pos_anterior == 0 && cant_tiempo > 0)) {
            System.out.println("no se encontro solucion");
        } else {
            imprimir_solucion(pos_solucion);
        }
        // } else {
        //    System.out.println("no se encontro solucion");

        //  }
        System.out.println();
        return false;

    }

    private static void imprimir_solucion(int pos_solucion) {

        for (int k = 0; k < pos_solucion; k++) {
            System.out.print("necesitaremos " + solucion[1][k] + " de " + solucion[0][k]);
            System.out.println();
        }

    }

    private static void reducir_solucion_anterior(int pos_solucion) {

        solucion[1][pos_solucion - 1] = solucion[1][pos_solucion - 1] - 1;

    }
}
