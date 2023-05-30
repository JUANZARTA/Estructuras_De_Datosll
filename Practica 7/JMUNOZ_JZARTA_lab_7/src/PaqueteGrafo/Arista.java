package PaqueteGrafo;
/**
 *
 * @author Juan P Martinez
 */
public class Arista {
    private Nodo nodo1;
    private Nodo nodo2;
    
    public Arista(Nodo nodo1, Nodo nodo2)
    {
	this.nodo1 = nodo1;
        this.nodo2 = nodo2;
    }
    
    public Nodo getNodo1()
    {
	return this.nodo1;
    }
    
    public Nodo getNodo2()
    {
	return this.nodo2;
    }
    
    public String toString()
    {
	return "(" + this.nodo1 + ", " + this.nodo2  + ")";
    }
    
}
