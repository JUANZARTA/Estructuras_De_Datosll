package jmunoz_jzarta_lab_8;

/**
 *
 * @author James - Zarta
 */
import java.util.ArrayList;

public class Grafo {

    private ArrayList<Nodo> nodos;
    private ArrayList<Arista> aristas;
    private Matriz matriz_adyacencia;
    private ArrayList<Nodo> nodos_recorridos;
    private ArrayList<Camino> caminos;

    private String camino;
    private int peso = 0;
    private char[] cont_nodos;// se usa para imprimir los indices de la matriz_adyacencia de adyacencia

    public Grafo() {
        this.nodos = new ArrayList<Nodo>();
        this.aristas = new ArrayList<Arista>();
        this.nodos_recorridos = new ArrayList<Nodo>();
        this.caminos = new ArrayList<Camino>();
    }

    public boolean insertarArista(char dato_1, char dato_2, int peso) {
        Nodo n1 = this.getNodo(dato_1);
        Nodo n2 = this.getNodo(dato_2);
        if (n1.equals(n2)) //los objetos Nodo son iguales?
        {
            return false;
        }

        Arista arista = new Arista(n1, n2, peso);
        if (this.getNodo(dato_1)== null) {
            if (this.insertarNodo(dato_1)) {
                System.out.println("Nodo insertado " + dato_1);
            } else {
                System.out.println("El nodo insertado ya existe");
            }
        }
           if (this.getNodo(dato_2)== null) {
            if (this.insertarNodo(dato_2)) {
                System.out.println("Nodo insertado " + dato_2);
            } else {
                System.out.println("El nodo insertado ya existe");
            }
        }
        aristas.add(arista);
        n1.insertarArista(arista);
        actualizar_matriz();

        return true;
    }

    public boolean insertarNodo(char dato) {
        Nodo nodo = new Nodo(dato);
        Nodo actual = getNodo(dato);
        if (actual != null) //existía previamente?
        {
            return false;
        } else {
            nodos.add(nodo);
            actualizar_matriz();
            return true;

        }
    }

    public Nodo getNodo(char dato) {
        Nodo nod = null;

        for (int i = 0; i < this.nodos.size(); i++) {
            nod = this.nodos.get(i);
            if (dato == nod.getDato()) {
                return nod;
            }
        }
        return null;
    }

    public Arista getArista(Nodo nodo1, Nodo nodo2) {
        Arista arista = null;

        for (int j = 0; j < nodo1.getNumVerticesVecinos(); j++) {
            if (!this.aristas.isEmpty()) {
                arista = nodo1.getArista(j);
                if (nodo2.equals(arista.getNodo2())) {
                    return arista;
                }
            } else {
                return null;

            }
        }
        return null;
    }

    private void actualizar_matriz() {
        this.matriz_adyacencia = new Matriz(this.nodos.size());

        for (int i = 0; i < this.nodos.size(); i++) {
            Nodo nodo_temp = this.nodos.get(i);
            for (int j = 0; j < nodo_temp.getNumVerticesVecinos(); j++) {
                if (!this.aristas.isEmpty()) {
                    Arista arista_temp = nodo_temp.getArista(j);
                    int indice_nod1 = this.nodos.indexOf(arista_temp.getNodo1());
                    int indice_nod2 = this.nodos.indexOf(arista_temp.getNodo2());
                    if (nodo_temp.getDato() == arista_temp.getNodo1().getDato()) {
                        this.matriz_adyacencia.agregar(indice_nod1, indice_nod2, arista_temp.getPeso());
                    } else {
                        this.matriz_adyacencia.agregar(indice_nod2, indice_nod1, arista_temp.getPeso());

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

        this.matriz_adyacencia.imprimir(cont_nodos);

    }

    public void buscar_caminos(char dato_fuente, char dato_sumidero) {
        this.camino = "" + dato_fuente;
        this.peso = 0;
        this.peso = this.peso + buscar_camino(dato_fuente, dato_sumidero);
    }

    private int buscar_camino(char dato_fuente, char dato_sumidero) {
        int n = this.matriz_adyacencia.get_n();

        Nodo nodo_fuente = this.getNodo(dato_fuente);
        int indice_fuente = this.nodos.indexOf(nodo_fuente);

        char a1 = this.getNodo(dato_fuente).getDato();
        char b1 = this.getNodo(dato_sumidero).getDato();

        if (a1 == b1) {
            System.out.println("\n sumidero alcanzado: ");
            System.out.println("    camino: " + this.camino + " costo: " + this.peso);
            guardar_camino(this.camino, this.peso);
            this.camino = this.camino.replaceAll(dato_sumidero + "", "");
            return 0;
        }

        for (int j = 0; j < n; j++) {
            if (this.matriz_adyacencia.get_valor_coordenada(indice_fuente, j) != 0) {

                char char_temp = this.nodos.get(j).getDato();

                if (!nodo_recorrido(char_temp)) {

                    agregar_nodo_recorrido(nodo_fuente);
                    this.peso = this.peso + this.matriz_adyacencia.get_valor_coordenada(indice_fuente, j);

                    this.camino += (char_temp);
                    this.peso = this.peso + buscar_camino(char_temp, dato_sumidero);
                    this.peso = this.peso - this.matriz_adyacencia.get_valor_coordenada(indice_fuente, j);
                    this.camino = this.camino.replaceAll(char_temp + "", "");
                }
            } else {
            }
        }
        return 0;
    }

    private void agregar_nodo_recorrido(Nodo nodo) {
        this.nodos_recorridos.add(nodo);
    }

    private boolean nodo_recorrido(char dato) {
        Nodo nod = null;
        if (!this.nodos_recorridos.isEmpty()) {
            for (int i = 0; i < this.nodos_recorridos.size(); i++) {
                nod = this.nodos_recorridos.get(i);
                if (dato == nod.getDato()) {
                    return true;
                }
            }

            return false;

        } else {
            return false;

        }
    }

    private void guardar_camino(String camino, int costo) {
        Camino camino_temp = new Camino(camino, costo);
        this.caminos.add(camino_temp);
    }

    public void costo_minimo() {
        String Camino1 = "";
        int Costo = -1;
        if (this.caminos.isEmpty()) {
            System.out.println("\n No se a calculado ningun camino ");
        } else {
            for (int i = 0; i < this.caminos.size(); i++) {
                int Costo_temp = this.caminos.get(i).getCosto();
                if (Costo == -1 || Costo > Costo_temp) {
                    Costo = Costo_temp;
                    Camino1 = this.caminos.get(i).getCamino();
                }
            }
            System.out.println("\n Camino mas corto: " + Camino1 + " costo: " + Costo);
        }
    }
}
