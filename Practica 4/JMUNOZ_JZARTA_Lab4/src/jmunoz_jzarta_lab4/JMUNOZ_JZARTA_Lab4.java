/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package jmunoz_jzarta_lab4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//Lab 4 EDD2 Prof. Juan Pablo Martinez P.
//Entregado por: JAMES MUÑOZ SOSCUE - JUAN CAMILO ZARTA CAMPO

public class JMUNOZ_JZARTA_Lab4 {

    public static void main(String[] args) {
        String instruccion = " ";
        int elemento;

        ArbolBin arbin = new ArbolBin();

        File f = new File("prueba_jmartinez4.txt");

        try ( Scanner entrada = new Scanner(f)) {

            while ((instruccion.equals("salir")) != true) {
                instruccion = entrada.next(); //se lee la instrucción
                System.out.println(instruccion);

                switch (instruccion) {
                    case "insertar":
                        elemento = entrada.nextInt();
                        // System.out.print(" " + elemento);
                        arbin.insertar(elemento);
                        break;
                    case "buscar":
                        elemento = entrada.nextInt();
                        // System.out.print(" " + elemento);
                        arbin.buscar(elemento, arbin.getRaiz());
                        break;
                    case "eliminar":
                        elemento = entrada.nextInt();
                        arbin.eliminar(elemento, arbin.getRaiz(), null, false, false);
                        System.out.print(" " + elemento);
                        
                        break;
                    case "preorden":
                        //System.out.println("Preorden:");
                        arbin.imprimirPreorden(arbin.getRaiz());
                        ;
                        break;
                    case "inorden":
                        // System.out.println("Inorden:");
                        arbin.imprimirInorden(arbin.getRaiz());
                        ;
                        break;
                    case "postorden":
                        // System.out.println("Postorden:");
                        arbin.imprimirPostorden(arbin.getRaiz());
                        ;
                        break;
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
