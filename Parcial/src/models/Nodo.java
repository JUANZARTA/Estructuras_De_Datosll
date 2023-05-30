package models;

public class Nodo<T> {
	private T elemento;
	private Nodo hIzq;
	private Nodo hDer;
	
	public Nodo(T elemento) {
		super();
		this.elemento = elemento;
		hIzq = hDer = null;
	}

	public T getElemento() {
		return elemento;
	}

	public void setElemento(T elemento) {
		this.elemento = elemento;
	}

	public Nodo gethIzq() {
		return hIzq;
	}

	public void sethIzq(Nodo hIzq) {
		this.hIzq = hIzq;
	}

	public Nodo gethDer() {
		return hDer;
	}

	public void sethDer(Nodo hDer) {
		this.hDer = hDer;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Nodo insertar(Nodo aux, Comparable elemento) {
		if (aux == null)
			return new Nodo(elemento);
		else if (elemento.compareTo(aux.getElemento()) < 0)
			aux.hIzq = insertar(aux.gethIzq(), elemento);
		else if (elemento.compareTo(aux.getElemento()) >= 0)
			aux.hDer = insertar(aux.gethDer(), elemento);
		
		return aux;
	}
}
