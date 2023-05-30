package jmunoz_jzarta_lab_8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * Lab 8 EDD2 - Red Fuente - Sumidero con costo minimo utilizando Grafos dirigidos con peso 
 * Prof. Juan Pablo Martinez P.
 * Entregado por: JAMES MUÑOZ SOSCUE - JUAN CAMILO ZARTA CAMPO
 */
public class JMUNOZ_JZARTA_Lab_8 {

    public static void main(String[] args) {

        Grafo objGrafo = new Grafo();

        String instruccion = " ";
        char dato_1;
        char dato_2;
        int peso;

        File f = new File("prueba_jp8.txt");

        try ( Scanner entrada = new Scanner(f)) {

            while ((instruccion.equals("fin")) != true) {
                instruccion = entrada.next(); //se lee la instrucción
                System.out.println(instruccion);

                switch (instruccion) {
                    case "nodo":
                        dato_1 = (entrada.next()).charAt(0);
                        if (objGrafo.insertarNodo(dato_1)) {
                            System.out.println("Nodo insertado " + dato_1);
                        } else {
                            System.out.println("El nodo insertado ya existe");
                        }
                        break;
                    case "arista":
                        dato_1 = (entrada.next()).charAt(0);
                        System.out.print(dato_1 + " ");
                        dato_2 = (entrada.next()).charAt(0);
                        System.out.print(dato_2 + " ");
                        peso = Integer.parseInt(entrada.next());
                        System.out.print(peso + " ");
                        if(objGrafo.insertarArista(dato_1, dato_2, peso)){
                             System.out.print("insertada ");
                        }else {
                            System.out.println("Arista no insertada");
                        }
                        break;
                    case "imprimir":
                        objGrafo.imprimir_grafo();
                        break;
                    case "matriz":
                        objGrafo.imprimir_matriz();

                        break;
                    case "fuente":
                        dato_1 = (entrada.next()).charAt(0);

                        instruccion = entrada.next(); //se lee la instrucción
                        dato_2 = (entrada.next()).charAt(0);
                        System.out.println(dato_1);

                        System.out.println(instruccion);
                        System.out.println(dato_2);
                        
                        objGrafo.buscar_caminos(dato_1,dato_2);
                        break;
                    case "costo":
                        objGrafo.costo_minimo();

                        break;
                    case "fin": 
                        ;
                        break;
                    default:
                        System.out.print("Instrucción desconocida");
                        break;
                }// se cierra el switch

                System.out.println();

            }//cierro el while principal

        } catch (FileNotFoundException e) {
            System.out.println(e.toString());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

}
