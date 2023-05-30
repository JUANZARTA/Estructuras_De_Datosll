package jmunoz_jzarta_lab_7_1;

/**
 *
 * @author James
 */
public class Arista {

    private Nodo nodo1;
    private Nodo nodo2;
    private int peso;

    public Arista(Nodo nodo1, Nodo nodo2, int peso) {
        this.nodo1 = nodo1;
        this.nodo2 = nodo2;
        this.peso = peso;

    }

    public Nodo getNodo1() {
        return this.nodo1;
    }

    public Nodo getNodo2() {
        return this.nodo2;
    }

    public int getPeso() {
        return this.peso;
    }

//    public String toString()
//    {
//	return "(" + this.nodo1 + ", " + this.nodo2  + ")";
//    }
}
