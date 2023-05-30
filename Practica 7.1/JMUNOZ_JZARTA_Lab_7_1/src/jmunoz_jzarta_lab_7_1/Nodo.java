package jmunoz_jzarta_lab_7_1;

/**
 *
 * @author James
 */

import java.util.ArrayList;

public class Nodo {

    private char dato;
    private ArrayList<Arista> aristasAdy;

    public Nodo(char dato) {
        this.dato = dato;
        this.aristasAdy = new ArrayList<Arista>();
    }

    public void insertarArista(Arista arista) {
        if (!this.aristasAdy.contains(arista)) {
            this.aristasAdy.add(arista);
        }
    }

    public char getDato() {
        return this.dato;
    }

    public Arista getArista(int indice) {
        return this.aristasAdy.get(indice);
    }

    public ArrayList<Arista> getAristas() {
        return new ArrayList<Arista>(this.aristasAdy);
    }

    public int getNumVerticesVecinos() {
        return this.aristasAdy.size();
    }

    public String toString() {
        return "Nodo " + this.dato;
    }

}
