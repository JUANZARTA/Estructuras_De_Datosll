package jmunoz_jzarta_lab_7_1;

/**
 *
 * @author James
 */
import java.util.ArrayList;

public class Grafo {

    private ArrayList<Nodo> nodos;
    private ArrayList<Arista> aristas;
    Matriz matriz;
    char[] cont_nodos;// se usa para imprimir los indices de la matriz de adyacencia

    public Grafo() {
        this.nodos = new ArrayList<Nodo>();
        this.aristas = new ArrayList<Arista>();
    }

    public boolean insertarArista(Nodo n1, Nodo n2, int peso) {
        if (n1.equals(n2)) //los objetos Nodo son iguales?
        {
            return false;
        }

        Arista arista = new Arista(n1, n2, peso);

        aristas.add(arista);
        n1.insertarArista(arista);
        n2.insertarArista(arista);
        actualizar_matriz();

        return true;
    }

    public boolean insertarNodo(char dato) {
        Nodo nodo = new Nodo(dato);
        nodos.add(nodo);
        actualizar_matriz();
        return true;
    }

//    public boolean insertarVertice(Nodo nodo) {
//        Nodo actual = this.nodos.get(nodo.getDato());
//        if (actual != null) //existía previamente?
//        {
//
//            // cod para sobreescribir
//        }
//
//        nodos.add(nodo);
//        return true;
//    }
    public Nodo getNodo(char dato) {
        Nodo nod = null;

        for (int i = 0; i < this.nodos.size(); i++) {
            nod = this.nodos.get(i);
            if (dato == nod.getDato()) {
                return nod;
            }
        }
        return nod;
    }

    private void actualizar_matriz() {
        this.matriz = new Matriz(this.nodos.size());

        for (int i = 0; i < this.nodos.size(); i++) {
            Nodo nodo_temp = this.nodos.get(i);
            for (int j = 0; j < nodo_temp.getNumVerticesVecinos(); j++) {
                if (!this.aristas.isEmpty()) {
                    Arista arista_temp = nodo_temp.getArista(j);
                    int indice_nod1 = this.nodos.indexOf(arista_temp.getNodo1());
                    int indice_nod2 = this.nodos.indexOf(arista_temp.getNodo2());
                    if (nodo_temp.getDato() == arista_temp.getNodo1().getDato()) {
                        this.matriz.agregar(indice_nod1, indice_nod2);
                    } else {
                        this.matriz.agregar(indice_nod2, indice_nod1);

                    }
                }
            }
        }

    }

    public void imprimir_grafo() {
        for (int i = 0; i < this.nodos.size(); i++) {
            Nodo nodo_temp = this.nodos.get(i);
            System.out.println("nodo: " + nodo_temp.getDato());
            for (int j = 0; j < nodo_temp.getNumVerticesVecinos(); j++) {
                if (!this.aristas.isEmpty()) {
                    Arista arista_temp = nodo_temp.getArista(j);
                    if (nodo_temp.getDato() == arista_temp.getNodo1().getDato()) {

                        System.out.print(arista_temp.getNodo1().getDato() + " ");
                        System.out.print(arista_temp.getNodo2().getDato() + " ");
                        System.out.println(arista_temp.getPeso() + " ");
                    } else {
                        System.out.print(arista_temp.getNodo2().getDato() + " ");

                        System.out.print(arista_temp.getNodo1().getDato() + " ");
                        System.out.println(arista_temp.getPeso() + " ");

                    }
                }
            }
        }
    }

    public void imprimir_matriz() {
        this.cont_nodos = new char[(this.nodos.size())];
        for (int i = 0; i < this.nodos.size(); i++) {
            this.cont_nodos[i] = this.nodos.get(i).getDato();
        }

        this.matriz.imprimir(cont_nodos);

    }
}
