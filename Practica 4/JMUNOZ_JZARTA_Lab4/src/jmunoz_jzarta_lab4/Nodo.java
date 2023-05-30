package jmunoz_jzarta_lab4;

public class Nodo {
   private int dato;
   private Nodo izquierda; //hijo izquierdo
   private Nodo derecha;  //hijo derecho
   
   public Nodo()
   {
       izquierda = null;
       derecha = null;
   }
   
   public Nodo(int d) //
    {
        this.dato = d;
        this.izquierda = null;
        this.derecha = null;
    }
    
    public Nodo(int d, Nodo izq, Nodo der) 
    {
        this.dato = d;
        this.izquierda = izq;
        this.derecha = der;
    }
    
    public void setDato(int dato)
    {
        this.dato = dato;
    }
    
    public int getDato()
    {
        return dato;
    }
    
    public void setIzquierda(Nodo izq)
    {
        this.izquierda = izq;
    }
    
    public Nodo getIzquierda()
    {
        return izquierda;
    }
        public void setDerecha(Nodo der)
    {
        this.derecha = der;
    }
    
    public Nodo getDerecha()
    {
        return derecha;
    }
}
