package models;

public class Arbol<T> {
	private Nodo raiz;
	private int tamano;
	private int acumulacion;
	private double variableX;
	private double sumatoria1;
	private double sumatoria2;
	private int numeroNivel; 

	private Arbol<T> nuevoArbol;
	
	public Arbol(Nodo raiz) {
		super();
		this.raiz = raiz;
	}

	public Nodo getRaiz() {
		return raiz;
	}

	public void setRaiz(Nodo raiz) {
		this.raiz = raiz;
	}
	
	public void insertar(Comparable elemento) {
		if (raiz == null)
			raiz = new Nodo(elemento);
		else
			raiz = raiz.insertar(raiz, elemento);
	}
	
	public void calcularFormula() {
		tamano = 0;
		variableX = 0;
		sumatoria1 = 0;
		sumatoria2 = 0;
		
		calcularTamanoAcumulacion(raiz);
		variableX = (double) acumulacion / (double) tamano;
		
		calcularSumatorias(raiz, variableX);
		double desviacion = (double) Math.sqrt(sumatoria1 / (double) tamano);
		
		double desviacionA4 = (double) Math.pow(desviacion, 4);

		double g2 = sumatoria2 / ((double) tamano * desviacionA4);

		
		if (g2>3)
			System.out.println("g2= "+ g2 + "; En este caso dado que g2 es mayor que 3, la distribución sería Leptocúrtica");
		else if (g2==3)
			System.out.println("g2= "+ g2 + "; En este caso dado que g2 es igual que 3, la distribución sería Mesocúrtica");
		else 
			System.out.println("g2= "+ g2 + "; En este caso dado que g2 es menor que 3, la distribución sería Platicúrtica");
		
	}
	
	private void calcularTamanoAcumulacion(Nodo aux) {
		if (aux != null) {
			calcularTamanoAcumulacion(aux.gethIzq());
			tamano++;
			acumulacion += Integer.parseInt(aux.getElemento().toString());
			calcularTamanoAcumulacion(aux.gethDer());
		}		
		
	}
	
	private void calcularSumatorias(Nodo aux, double variableX) {
		if (aux != null) {
			calcularSumatorias(aux.gethIzq(), variableX);
			sumatoria1+= Math.pow((Double.parseDouble(aux.getElemento().toString()) - variableX), 2) ;
			sumatoria2+= Math.pow((Double.parseDouble(aux.getElemento().toString()) - variableX), 4) ;
			calcularSumatorias(aux.gethDer(), variableX);
		}		
		
	}
	

	public void imprimirArbol() {
		imprimirArbol(raiz, 0);
	}
	
	public Arbol() {
		super();
	}
	
	public void imprimirArbol(Nodo aux, int n) {
		if (aux != null) {
			System.out.println(n + " " + aux.getElemento());
			imprimirArbol(aux.gethIzq(), n+1);
			imprimirArbol(aux.gethDer(), n+1);
		}
	}
	
	
	
	public void podarNivel(int nivel) {
		nivelMax();
		if (nivel == 0)
			System.out.println("No se puede podar el árbol en este nivel.");
		else if (nivel <= numeroNivel){
			nuevoArbol = new Arbol<>();
			podarNivel(raiz, 0, nivel);

		} else 
			System.out.println("No existe este nivel en el árbol.");
		
			
	}
	
	public void nivelMax() {
		numeroNivel =0;
		nivelMax(raiz, 0);

	}
	
	public void nivelMax(Nodo aux, int n) {
		if (aux != null) {
			if (numeroNivel < n)
				numeroNivel = n;	
			nivelMax(aux.gethIzq(), n+1);
			nivelMax(aux.gethDer(), n+1);
		}
		
	}
	
	
	public void podarNivel(Nodo aux, int n, int nivel) {
		if (aux != null) {
			if (n < nivel)
				System.out.println("Nivel: " + n + "; Dato: "+ aux.getElemento());
			podarNivel(aux.gethIzq(), n+1, nivel);
			podarNivel(aux.gethDer(), n+1, nivel);
		}
	}
	
	
	
	public void mostrarValoresInteriores() {
		System.out.print("Nodos interiores:");
		mostrarValoresInteriores(raiz, 0);
		System.out.println();
	}
	
	public void mostrarValoresInteriores(Nodo aux, int n) {
		if (aux != null) {
			mostrarValoresInteriores(aux.gethIzq(), n+1);
			if ((aux.gethIzq() != null || aux.gethDer() != null) && n != 0)
				System.out.print(" " + aux.getElemento());
			mostrarValoresInteriores(aux.gethDer(), n+1);
		}
		
	}
	
	
	

	
	
	
		
}
