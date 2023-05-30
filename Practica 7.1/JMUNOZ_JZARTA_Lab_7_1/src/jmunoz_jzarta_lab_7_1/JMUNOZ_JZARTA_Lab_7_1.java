
package jmunoz_jzarta_lab_7_1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * 
 *  Lab 7.1 EDD2 - Grafos no dirigidos con peso 
 *  Prof. Juan Pablo Martinez P.
 *  Entregado por: JAMES MUÑOZ SOSCUE - JUAN CAMILO ZARTA CAMPO
 */
public class JMUNOZ_JZARTA_Lab_7_1 {

    
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
                        objGrafo.insertarNodo(dato_1);
                        System.out.println("insertado " + dato_1);
                        break;
                    case "arista":
                        dato_1 = (entrada.next()).charAt(0);
                        Nodo objNodo1 = objGrafo.getNodo(dato_1);
                        System.out.print(dato_1 + " ");
                        dato_2 = (entrada.next()).charAt(0);
                        Nodo objNodo2 = objGrafo.getNodo(dato_2);
                        System.out.print(dato_2 + " ");
                        peso = Character.getNumericValue((entrada.next()).charAt(0));
                        System.out.print(peso + " ");
                        System.out.print("insertada ");
                        objGrafo.insertarArista(objNodo1, objNodo2, peso);
                        break;
                    case "imprimir":
                        objGrafo.imprimir_grafo();
                        break;
                    case "matriz":
                        objGrafo.imprimir_matriz();

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
