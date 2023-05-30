package jmunoz_jzarta_lab6;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


public class main {
    public static void main(String[] args) throws IOException {

        // Creando un nuevo objeto Arbol2-3 llamado objArbol23
        Arbol23 objArbol23 = new Arbol23();

        String instruccion=" ";
        int elemento;

        File f = new File("c:/archivos/prueba_jmartinez23.txt");
       
        try  (Scanner entrada = new Scanner(f)) {

            while ((instruccion.equals("salir")) != true)
            { 
                instruccion = entrada.next(); //se lee la instrucción
                System.out.print(instruccion);
                
                switch(instruccion)
                {        
                    case "insertar":
                        elemento = entrada.nextInt();
                        boolean insertado = objArbol23.insertar(elemento);
                        System.out.print(" "+elemento)
                        ;break;
                    case "buscar":
                        elemento = entrada.nextInt();
                        boolean encontrado = objArbol23.buscar(elemento);
                        System.out.print(" "+elemento);
                        if (encontrado) 
                        {
                            System.out.printf(" encontrado");
                        } 
                        else 
                        {
                            System.out.printf("No encontrado");
                        } 
                        ;break;
                    case "imprimirOrden":
                        objArbol23.ListarElementosOrdenados();
                        ;break;
                    case "imprimirNiveles":
                        objArbol23.ListarNiveles(); 
                        ;break;
                    case "salir": 
                        ;break;
                    default: System.out.print("Instrucción desconocida"); break;      
                }// se cierra el switch
                
                System.out.println();
                    
            }//cierro el while principal

        } catch (FileNotFoundException e) {
            System.out.println(e.toString());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    } //cierra el main
}
