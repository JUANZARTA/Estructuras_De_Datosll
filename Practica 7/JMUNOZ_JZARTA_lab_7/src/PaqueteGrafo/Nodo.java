package PaqueteGrafo;

import java.util.ArrayList;

/**
 *
 * @author Juan P Martinez
 */
public class Nodo {
    private char dato;
    private ArrayList<Arista> ady;
  
    
    public Nodo (char dato)
    {
        this.dato = dato;
        this.ady = new ArrayList<Arista>();
    }
    
   public void insertarArista(Arista arista)
    {
	if( !this.ady.contains(arista))
	    this.ady.add(arista);
    }
   
   public char getDato()
    {
	return this.dato;
    }
   
       public Arista getArista(int indice)
    {
	return this.ady.get(indice);
    }
  
   public ArrayList<Arista> getAristas()
    {
	return new ArrayList<Arista>(this.ady);
    }
   
    public int getContarVerticesVecinos()
    {
	return this.ady.size();
    }
      
    public String toString()
    {
	return "Nodo " + this.dato;
    }

}
