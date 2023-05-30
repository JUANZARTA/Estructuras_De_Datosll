package presentation;

import java.util.Scanner;

import models.Arbol;

public class Menu {
	static Scanner scanner = new Scanner(System.in);
	
	public static void imprimirMenu() {
		System.out.println("----Parcial N°1 Lab. Est de Datos 2----");
		System.out.println("1. Mostrar G2.");
		System.out.println("2. Podar Arbol.");
		System.out.println("3. Salir.");
	}
	
	public static void ingresarOpcion(Arbol<Integer> arbol) {
		
		int opcion = 0;
		do {
			
			imprimirMenu();
			try {
				String entrada =  scanner.next();
				opcion = Integer.parseInt(entrada);
			} catch (Exception e) {
				opcion = 0;
			}
			
			
			switch (opcion) {
				case 1:
					arbol.calcularFormula();
					break;
				case 2:
					ingresarOpcion2(arbol);
					break;
				case 3:
					System.out.println("Adiós...");
					break;
				
				default:
					System.out.println("Error, usted a ingresado una opción no valida");
			}
			
		} while (opcion != 3);
	}
	
	public static void ingresarOpcion2(Arbol<Integer> arbol) {
		
		boolean correcto = false;
		int nivel = -1;
		do {
			
			try {
				System.out.println("Ingrese el nivel a podar: ");
				String entrada =  scanner.next();
				nivel = Integer.parseInt(entrada);
				arbol.podarNivel(nivel);
				correcto = true;
			} catch (Exception e) {
				System.out.println("Error, usted no ingreso un dato valido, ");
			}
			
		} while (!correcto);
	}


}
