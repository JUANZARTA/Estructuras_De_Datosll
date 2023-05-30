package jmunoz_jzarta_lab5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class JMUNOZ_JZARTA_Lab5 {

    public static void main(String[] args) {
        
//Lab 5 EDD2 Prof. Juan Pablo Martinez P.
// Entregado por: JAMES MUÑOZ SOSCUE - JUAN CAMILO ZARTA CAMPO

        String instruccion = " ";
        int elemento;

        ArbolAvl arbavl = new ArbolAvl();

        File f = new File("prueba_jmartinez_lab5.txt");

        try ( Scanner entrada = new Scanner(f)) {

            while ((instruccion.equals("salir")) != true) {
                instruccion = entrada.next(); //se lee la instrucción
                System.out.println(instruccion);

                switch (instruccion) {
                    case "insertar":
                        elemento = entrada.nextInt();
                         System.out.print(" " + elemento);
                        arbavl.insertar(elemento);
                        break;
                    case "buscar":
                        elemento = entrada.nextInt();
                        // System.out.print(" " + elemento);
                        arbavl.buscar(elemento, arbavl.getRaiz());
                        break;
//                    case "eliminar":
//                        elemento = entrada.nextInt();
//                        arbavl.eliminar(elemento, arbavl.getRaiz(), null, false, false);
//                        System.out.print(" " + elemento);
//
//                        break;
                    case "preorden":
                        //System.out.println("Preorden:");
                        arbavl.imprimirPreorden(arbavl.getRaiz());
                        ;
                        break;
                    case "inorden":
                        // System.out.println("Inorden:");
                        arbavl.imprimirInorden(arbavl.getRaiz());
                        ;
                        break;
                    case "postorden":
                        // System.out.println("Postorden:");
                        arbavl.imprimirPostorden(arbavl.getRaiz());
                        ;
                        break;
//                    case "elementos":
//                        int contador = 0;
//                        contador = arbavl.elementos(arbavl.getRaiz());
//                        System.out.print(contador + " ");
//
//                        ;
//                        break;
//                    case "mayor":
//                        arbavl.mayor(arbavl.getRaiz());
//                        ;
//                        break;
//                    case "menor":
//                        arbavl.menor(arbavl.getRaiz());
//                        ;
//                        break;
                    case "salir": 
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
