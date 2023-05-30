
package jmunoz_jzarta_lab5;

public class NodoAvl {

    private int dato;
    private int Alt;      // Altura del nodo
    private NodoAvl izquierda; //hijo izquierdo
    private NodoAvl derecha;  //hijo derecho

    public NodoAvl() {
        izquierda = null;
        derecha = null;
    }

    public NodoAvl(int d, int Alt) //
    {
        this.Alt = Alt;
        this.dato = d;
        this.izquierda = null;
        this.derecha = null;
    }

    public NodoAvl(int d, NodoAvl izq, NodoAvl der, int Alt) {
        this.Alt = Alt;
        this.dato = d;
        this.izquierda = izq;
        this.derecha = der;
    }

    public void setAlt(int Alt) {
        this.Alt = Alt;
    }

    public int getAlt() {
        return Alt;
    }

    public void setDato(int dato) {
        this.dato = dato;
    }

    public int getDato() {
        return dato;
    }

    public void setIzquierda(NodoAvl izq) {
        this.izquierda = izq;
    }

    public NodoAvl getIzquierda() {
        return izquierda;
    }

    public void setDerecha(NodoAvl der) {
        this.derecha = der;
    }

    public NodoAvl getDerecha() {
        return derecha;
    }

}
