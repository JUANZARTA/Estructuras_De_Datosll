package PaqueteGrafo;

import java.util.ArrayList;

/**
 *
 * @author Juan P Martinez
 */
public class Grafo {
    private ArrayList<Nodo> nodos;
    private ArrayList<Arista> aristas;
    
   public Grafo()
    {
	this.nodos = new ArrayList<Nodo>();
	this.aristas = new ArrayList<Arista>();
    }
   
   
    public boolean insertarArista(Nodo n1, Nodo n2)
    {
	if(n1.equals(n2)) //los objetos Nodo son iguales?
	    return false;

	Arista arista = new Arista(n1, n2);

	aristas.add(arista);
	n1.insertarArista(arista);
	n2.insertarArista(arista);
	return true;
    }
    public boolean insertarNodo(char dato)
    {
        Nodo nodo= new Nodo(dato);
	nodos.add( nodo);
	return true;
    }
       
    public boolean insertarVertice(Nodo nodo)
    {
	Nodo actual = this.nodos.get(nodo.getDato());
	if(actual != null) //existía previamente?
	    {

		// cod para sobreescribir
		
	    }

	nodos.add( nodo);
	return true;
    }
    
  public Nodo getNodo(char dato)
    {
	return this.nodos.get(dato);
    }
   
}
