package presentation;

import models.Arbol;

public class Test {
	public static void main(String []args) {
		
		Arbol<Integer> arbolEnteros = new Arbol<>();
		
		insertarDatos(arbolEnteros);
		
		//arbolEnteros.calcularFormula();
		
		Menu.ingresarOpcion(arbolEnteros);
		return;
	}
	
	public static void insertarDatos(Arbol arbol) {
		String [] listaDatos = Archivo.pasarAVector();
		
		for (String i : listaDatos) {
			try {arbol.insertar(Integer.parseInt(i));} catch (Exception e) {}
		}
	}
	
}
