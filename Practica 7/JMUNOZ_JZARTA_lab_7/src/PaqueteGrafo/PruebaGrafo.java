package PaqueteGrafo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author Juan P Martinez
 */
public class PruebaGrafo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        Grafo objGrafo = new Grafo();
       // Nodo objNodo1 = new Nodo('a');
        Nodo objNodo2 = new Nodo('b');
        Nodo objNodo3 = new Nodo('c');
        
              
//Lab 7 EDD2 Prof. Juan Pablo Martinez P.
// Entregado por: JAMES MUÑOZ SOSCUE - JUAN CAMILO ZARTA CAMPO

        String instruccion = " ";
        char nodo_1;
        char nodo_2;

        File f = new File("prueba_jp7.txt");

        try ( Scanner entrada = new Scanner(f)) {

            while ((instruccion.equals("fin")) != true) {
                instruccion = entrada.next(); //se lee la instrucción
                System.out.println(instruccion);

                switch (instruccion) {
                    case "nodo":
                        nodo_1 = (entrada.next()).charAt(0);
                        objGrafo.insertarNodo(nodo_1);
                        System.out.println("insertado " + nodo_1);
                        break;
                    case "arista":
                        nodo_1 = (entrada.next()).charAt(0);
                        objGrafo.insertarNodo(nodo_1);
                        System.out.println(nodo_1);
                        nodo_2 = (entrada.next()).charAt(0);
                        objGrafo.insertarNodo(nodo_2);
                        System.out.println(nodo_2);

                        break;
                    case "imprimir":
//                        nodo_1 = (entrada.next()).charAt(0);
//                          System.out.println(nodo_1);
//
//                        nodo_2 = entrada.next();
//                         System.out.println(nodo_2);
//
//                        break;
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


//        System.out.println(Character.toString(objNodo1.getDato()));
//        System.out.println(Character.toString(objNodo2.getDato()));
//        
//
//        objGrafo.insertarArista(objNodo1, objNodo2);
//        objGrafo.insertarArista(objNodo1, objNodo3);
//        objGrafo.insertarArista(objNodo3, objNodo2);
//
//
//        System.out.println(objNodo1.getArista(0).toString()); 
//        System.out.println(objNodo1.getArista(1).toString());
//        System.out.println(objNodo2.getArista(0).toString());
//        System.out.println(objNodo2.getArista(1).toString());
//
// 

      
    }
    
}
