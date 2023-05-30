import java.util.Queue;
import java.util.LinkedList;

public class JMUNOZ_JZARTA_Lab6{

    private int cant_nodos;
    private Nodo raiz;
    private boolean insercionRealizada;
    private boolean split;
    private boolean first;

    private enum Nodes {
        IZQ, MED, DER, TEMP;
    }

    // Constructor por defecto
    public JMUNOZ_JZARTA_Lab6() {
        // Inicializando todo a los valores predeterminados
        cant_nodos = 0;
        raiz = null;
        insercionRealizada = false;
        split = false;
        first = false;
    } // finaliza el constructor

    // Clase Node
    private class Node {

    }

    // La clase Nodo hereda de Node
    private class Nodo extends Node {

        int[] datos;             // datos para buscar
        Node[] hijos;        // referencias a los 2 o 3 hijos
        int grado;             // número de hijos que tiene cada nodo

        // Constructor por defecto
        Nodo() {
            // Inicializando variables
            datos = new int[2];          // datos[0] = min dato en el subárbol medio
            hijos = new Node[3];     // referencias a hijos de hojas
            grado = 0;                 // para operaciones de impresión, desbordamiento y división
        } // constructor finaliza

        // Metodo imprimir
        void imprimir() {

            switch (grado) {
                case 1:
                    System.out.print("[-,-]");
                    break;
                case 2:
                    System.out.print("[" + datos[0] + ",-] ");
                    break;
                default:
                    System.out.print("[" + datos[0] + "," + datos[1] + "] ");
                    break;
            }
        } // finaliza imprimir()
    } // Finaliza Nodo

    // La clase NodoHoja hereda de Node 
    private class NodoHoja extends Node {

        int dato;    // para almacenar el valor

        NodoHoja(int dato) {
            this.dato = dato;
        }

    } // NodoHoja finaliza

    // Fusionar raíz: si el nodo raiz tuvo que dividirse, lo fusiona
    // volver a un solo Nodo
    private void insertDato(int dato) {
        // Crear un arreglo de Nodo de tamaño 2
        // para capturar los 2 nodos
        Node[] array = new Node[2];
        // Llame al método para insertar el dato
        array = insertar(dato, raiz);

        // Compruebe si el segundo valor en el arreglo es nulo o no
        if (array[1] == null) {
            // Si es nulo, significa que no hay necesidad de fusionar el arreglo
            raiz = (Nodo) array[0]; // Simplemente asigne la raiz al primer valor
        } else {
            // Tenemos dos valores en el arreglo
            // y ahora tenemos que fusionarlos en un solo nodo

             // Crea un nuevo Nodo y adjunta el primer y segundo elemento en el
             // arreglo como referencia en el nodo recién creado
            Nodo raizArbol = new Nodo();
            raizArbol.hijos[0] = array[0];
            raizArbol.hijos[1] = array[1];
            actualizarArbol(raizArbol);   // Actualizar el nuevo nodo
            raiz = raizArbol;        // Asignar raiz a este nodo
        } 
    } // insertDato() finaliza

    // insertar: Devuelve un arreglo de nodos (máx. 2 nodos)
     //después de insertar el dato
    private Node[] insertar(int dato, Node n) {

        // Cree un nuevo arreglo Node de tamaño 2
        Node array[] = new Node[2];     // [0] antiguo Node, [1] = nuevo Node

        // Este arreglo de nodos almacena el resultado después de que la inserción recursiva hace return
        Node catchArray[] = new Node[2];

        Nodo t = null;  // Inicializa t como nulo

        // Si n is un Nodo (una instancia de Nodo)
        if (n instanceof Nodo) {
            t = (Nodo) n;
        }

        // Si la raiz es nula, significa que es el primer nodo
        if (raiz == null && !first) {
            first = true;   // Cambie para hacer esto si es falso para la próxima llamada recursiva

            // Creando un nuevo nodo
            Nodo newNode = new Nodo();
            t = newNode;
            // Llamar a insertar con el valor dado
            t.hijos[0] = insertar(dato, t.hijos[0])[0];
            actualizarArbol(t); 

            // devolveremos este arreglo
            // Hace que el primer elemento en el arreglo almacene la referencia de este Nodo
            array[0] = t;
            array[1] = null;    // Hace el segundo elemento nulo

        } // Si el nodo en el que se llamó a insertar es un treeNode y
         // almacena referencias a TreeNodes
        else if (t != null && !(t.hijos[0] instanceof NodoHoja)) {
            if (dato < t.datos[0]) {
                catchArray = insertar(dato, t.hijos[0]);
                t.hijos[0] = catchArray[0];

                if (split) {
                    if (t.grado <= 2) {
                        split = false;
                        t.hijos[2] = t.hijos[1];
                        t.hijos[1] = catchArray[1];
                        actualizarArbol(t);
                        array[0] = t;
                        array[1] = null;
                    } else if (t.grado > 2) {
                        Nodo newNode = new Nodo();
                        newNode.hijos[0] = t.hijos[1];
                        newNode.hijos[1] = t.hijos[2];
                        actualizarArbol(newNode);
                        t.hijos[1] = catchArray[1];
                        t.hijos[2] = null;
                        actualizarArbol(t);
                        array[0] = t;
                        array[1] = newNode;
                    }
                } else {
                    actualizarArbol(t);
                    array[0] = t;
                    array[1] = null;
                } 
            } 
            else if (dato >= t.datos[0] && (t.hijos[2] == null || dato < t.datos[1])) {
                catchArray = insertar(dato, t.hijos[1]);
                t.hijos[1] = catchArray[0];

                if (split) {
                    if (t.grado <= 2) {
                        split = false;
                        t.hijos[2] = catchArray[1];
                        actualizarArbol(t);
                        array[0] = t;
                        array[1] = null;
                    } else if (t.grado > 2) {
                        Nodo newNode = new Nodo();
                        newNode.hijos[0] = catchArray[1];
                        newNode.hijos[1] = t.hijos[2];
                        actualizarArbol(newNode);
                        t.hijos[2] = null;
                        actualizarArbol(t);
                        array[0] = t;
                        array[1] = newNode;
                    }
                } else {
                    actualizarArbol(t);
                    array[0] = t;
                    array[1] = null;
                } 
            } 
            else if (dato >= t.datos[1]) {
                catchArray = insertar(dato, t.hijos[2]);
                t.hijos[2] = catchArray[0];

                if (split) {
                    if (t.grado > 2) {
                        Nodo newNode = new Nodo();
                        newNode.hijos[0] = catchArray[0];
                        newNode.hijos[1] = catchArray[1];
                        actualizarArbol(newNode);
                        t.hijos[2] = null;
                        actualizarArbol(t);
                        array[0] = t;
                        array[1] = newNode;

                    }
                } else {
                    actualizarArbol(t);
                    array[0] = t;
                    array[1] = null;
                } 
            } 
        } 
        else if (t != null && t.hijos[0] instanceof NodoHoja) {
            NodoHoja l1 = null;
            NodoHoja l2 = null; 
            NodoHoja l3 = null;
            if (t.hijos[0] != null && t.hijos[0] instanceof NodoHoja) {
                l1 = (NodoHoja) t.hijos[0];
            }
            if (t.hijos[1] != null && t.hijos[1] instanceof NodoHoja) {
                l2 = (NodoHoja) t.hijos[1];
            }
            if (t.hijos[2] != null && t.hijos[2] instanceof NodoHoja) {
                l3 = (NodoHoja) t.hijos[2];
            }

            if (t.grado <= 2) {
                if (t.grado == 1 && dato > l1.dato) {
                    NodoHoja leaf = new NodoHoja(dato);
                    t.hijos[1] = leaf;
                } else if (t.grado == 1 && dato < l1.dato) {
                    NodoHoja leaf = new NodoHoja(dato);
                    t.hijos[1] = l1;
                    t.hijos[0] = leaf;
                } else if (t.grado == 2 && dato < l1.dato) {
                    NodoHoja leaf = new NodoHoja(dato);
                    t.hijos[2] = l2;
                    t.hijos[1] = l1;
                    t.hijos[0] = leaf;
                } else if (t.grado == 2 && dato < l2.dato && dato > l1.dato) {
                    NodoHoja leaf = new NodoHoja(dato);
                    t.hijos[2] = l2;
                    t.hijos[1] = leaf;
                } else if (t.grado == 2) {
                    NodoHoja leaf = new NodoHoja(dato);
                    t.hijos[2] = leaf;
                }

                actualizarArbol(t);
                array[0] = t;
                array[1] = null;
            } 
            else if (t.grado > 2) {
                split = true;

                if (dato < l1.dato) {
                    NodoHoja hoja = new NodoHoja(dato);
                    Nodo newNode = new Nodo();
                    t.hijos[0] = hoja;
                    t.hijos[1] = l1;
                    t.hijos[2] = null;
                    actualizarArbol(t);
                    newNode.hijos[0] = l2;
                    newNode.hijos[1] = l3;
                    actualizarArbol(newNode);
                    array[0] = t;
                    array[1] = newNode;
                } else if (dato >= l1.dato && dato < l2.dato) {
                    NodoHoja hoja = new NodoHoja(dato);
                    Nodo newNode = new Nodo();
                    t.hijos[1] = hoja;
                    t.hijos[2] = null;
                    actualizarArbol(t);
                    newNode.hijos[0] = l2;
                    newNode.hijos[1] = l3;
                    actualizarArbol(newNode);
                    array[0] = t;
                    array[1] = newNode;
                } else if (dato >= l2.dato && dato < l3.dato) {
                    NodoHoja hoja = new NodoHoja(dato);
                    t.hijos[2] = null;
                    actualizarArbol(t);
                    Nodo newNode = new Nodo();
                    newNode.hijos[0] = hoja;
                    newNode.hijos[1] = l3;
                    actualizarArbol(newNode);
                    array[0] = t;
                    array[1] = newNode;
                } else if (dato >= l3.dato) {
                    NodoHoja hoja = new NodoHoja(dato);
                    t.hijos[2] = null;
                    actualizarArbol(t);
                    Nodo newNode = new Nodo();
                    newNode.hijos[0] = l3;
                    newNode.hijos[1] = hoja;
                    actualizarArbol(newNode);
                    array[0] = t;
                    array[1] = newNode;
                } 
            } 
            insercionRealizada = true;
        } else if (n == null) {
            insercionRealizada = true;
            array[0] = new NodoHoja(dato);
            array[1] = null;
            return array;
        }

        return array;
    } 

    private void actualizarArbol(Nodo t) {

        if (t != null) {
            if (t.hijos[2] != null && t.hijos[1] != null && t.hijos[0] != null) {
                t.grado = 3;
                t.datos[0] = getValoresNodo(t, Nodes.IZQ);
                t.datos[1] = getValoresNodo(t, Nodes.DER);
            } else if (t.hijos[1] != null && t.hijos[0] != null) {
                t.grado = 2;
                t.datos[0] = getValoresNodo(t, Nodes.IZQ);
                t.datos[1] = 0;
            } else if (t.hijos[0] != null) {
                t.grado = 1;
                t.datos[1] = t.datos[0] = 0;
            } 
        } 
    } 

    // Este método devuelve el valor de los datos del Nodo
    private int getValoresNodo(Node n, Nodes whichVal) {
        // inicializa dato en -1
        int valor = -1;

        // Para determinar el tipo de nodo
        Nodo t = null;
        NodoHoja l = null;
        if (n instanceof Nodo) {
            t = (Nodo) n;
        } else {
            l = (NodoHoja) n;
        }

        // Si es nodo hoja retorna el dato
        if (l != null) {
            valor = l.dato;
        }
        // Si es un nodo
        if (t != null) {
            // Comprobar para qué dato queremos obtener valor
            if (null != whichVal) {
                switch (whichVal) {
                    // Si es el dato izquierdo
                    case IZQ:
                        // Vaya al nodo medio y luego siga hacia
                         // 1er hijo usando llamadas recursivas
                        valor = getValoresNodo(t.hijos[1], Nodes.TEMP);
                        break;
                    // Si es el dato derecho
                    case DER:
                        // Vaya al nodo derecho y luego siga hacia
                         // 1er hijo usando llamadas recursivas
                        valor = getValoresNodo(t.hijos[2], Nodes.TEMP);
                        break;
                    // Este caso se ejecuta después de haber determinado a qué nodo queremos ir
                    case TEMP:
                        valor = getValoresNodo(t.hijos[0], Nodes.TEMP);
                        break;

                    default:
                        break;
                } // switch finaliza
            } 
        } 

        return valor;
    } // getValoresNodo() finaliza

    private boolean buscar(int key, Node n) {
        boolean found = false;

        // Determina el tipo de nodo
        Nodo t = null;
        NodoHoja l = null;
        if (n instanceof Nodo) {
            t = (Nodo) n;
        } else {
            l = (NodoHoja) n;
        }

        // Si es un Nodo
        if (t != null) {
            // si tienen grado 1
            if (t.grado == 1) {
                // Continue buscar en nodo inquierdo
                found = buscar(key, t.hijos[0]);
            } // Si tiene un grado de 2 y el valor de dato es menor que 1er dato
            else if (t.grado == 2 && key < t.datos[0]) {
                // Continue buscar en nodo izquierdo
                found = buscar(key, t.hijos[0]);
            } // Si tiene un grado de 2 y el valor de dato es mayor que 1er dato
            else if (t.grado == 2 && key >= t.datos[0]) {
                // Continuar buscar en middle node
                found = buscar(key, t.hijos[1]);
            } // Si tiene grado 3 y el valor a buscar es menor al 1er dato
            else if (t.grado == 3 && key < t.datos[0]) {
                // Continuar la búsqueda en el nodo izquierdo
                found = buscar(key, t.hijos[0]);
            } // If it has a grado of 3 and the value to be searched for is in between 1st and 2nd dato
            else if (t.grado == 3 && key >= t.datos[0] && key < t.datos[1]) {
                // Continue buscar in middle node
                found = buscar(key, t.hijos[1]);
            } // Si tiene grado 3 y el valor a buscar esta entre 1° y 2° dato
            else if (t.grado == 3 && key >= t.datos[1]) {
                // Continuar buscar en node derecho
                found = buscar(key, t.hijos[2]);
            } 
        } // Si es un nodo hoja y el valor coincide con el que estamos buscando
        else if (l != null && key == l.dato) {
            return true;
        } 

        return found;
    } // buscar() finaliza

    // Este método imprime los datos almacenados en los Nodos hoja en orden
    private void ListarElementosOrdenados(Node n) {

        // Determina el tipo de nodo
        Nodo t = null;
        NodoHoja l = null;
        if (n instanceof Nodo) {
            t = (Nodo) n;
        } else {
            l = (NodoHoja) n;
        }

        // Si es Nodo
        if (t != null) {
            // Si el primer hijo no es nulo
            if (t.hijos[0] != null) {
                // Continuar llamada recursiva en el primer hijo
                ListarElementosOrdenados(t.hijos[0]);
            }

            // Si el segundo hijo no es nulo
            if (t.hijos[1] != null) {
                // Continuar llamada recursiva en el segundo hijo
                ListarElementosOrdenados(t.hijos[1]);
            }

            // Si el tercer hijo no es nulo
            if (t.hijos[2] != null) {
                // Continuar llamada recursiva en el tercer hijo
                ListarElementosOrdenados(t.hijos[2]);
            }
        } // Si es nodo Hoja
        else if (l != null) {
            System.out.print(l.dato + " ");
        } 
    } // ListarElementosOrdenados() finaliza

    // Este método solo imprime los datos en cada nivel
    private void ListarNiveles(Node n) {
        // Hacer dos colas nuevas para almacenar cada nivel alternando en la cola
        Queue<Node> colaUno = new LinkedList<>();
        Queue<Node> colaDos = new LinkedList<>();

        // Si el primer nodo es nulo, salga; de lo contrario, continúe
        if (n == null) {
            return;
        }

        // Agregar el primer nodo a la primera cola
        colaUno.add(n);

        // Se declara dos variables de nodo para contener los nodos
        Node first = null;
        Nodo t = null;

        // Bucle hasta que ambas colas no estén vacías
        while (!colaUno.isEmpty() || !colaDos.isEmpty()) {

            while (!colaUno.isEmpty()) {
                // Toma el primer elemento de la cola.
                first = colaUno.poll();
                // Si es un nodo árbol imprimir el nodo
                if (first instanceof Nodo) {
                    t = (Nodo) first;
                    t.imprimir();
                }

                // Ahora agregue todos los hijos en el siguiente nivel a la segunda cola
                if (t.hijos[0] != null && !(t.hijos[0] instanceof NodoHoja)) {
                    colaDos.add(t.hijos[0]);
                }
                if (t.hijos[1] != null && !(t.hijos[1] instanceof NodoHoja)) {
                    colaDos.add(t.hijos[1]);
                }
                if (t.hijos[2] != null && !(t.hijos[2] instanceof NodoHoja)) {
                    colaDos.add(t.hijos[2]);
                }

            } 
            // Deje una línea en blanco después de imprimir un nivel
            if (!colaUno.isEmpty() || !colaDos.isEmpty()) {
                System.out.println();
            }

            // Si la segunda cola no está vacía
            while (!colaDos.isEmpty()) {
                first = colaDos.poll();

                if (first instanceof Nodo) {
                    t = (Nodo) first;
                    t.imprimir();
                }

                if (t.hijos[0] != null && !(t.hijos[0] instanceof NodoHoja)) {
                    colaUno.add(t.hijos[0]);
                }
                if (t.hijos[1] != null && !(t.hijos[1] instanceof NodoHoja)) {
                    colaUno.add(t.hijos[1]);
                }
                if (t.hijos[2] != null && !(t.hijos[2] instanceof NodoHoja)) {
                    colaUno.add(t.hijos[2]);
                }

            } 
            if (!colaUno.isEmpty() || !colaDos.isEmpty()) {
                System.out.println();
            }
        } 

        System.out.println();
        ListarElementosOrdenados(raiz);
        System.out.println();
    } // ListarNiveles() finaliza

    // Este es un método público para llamar a insertar
    public boolean insertar(int dato) {
        boolean insertar = false;
        split = false;

        // Comprobar si el dato está presente o no en el árbol
        if (!buscar(dato)) {
            insertDato(dato);
        }

        // Si la inserción fue exitosa
        if (insercionRealizada) {
            // Incrementa el tamaño y devuelve el éxito.
            cant_nodos++;
            insertar = insercionRealizada;
            insercionRealizada = false;
        }
        return insertar;
    } // insertar() finaliza

    // Un método público para llamar a buscar
    public boolean buscar(int elemento) {
        return buscar(elemento, raiz);
    } 


    // Este es un método público para llamar  a ListarElementosOrdenados
    public void ListarElementosOrdenados() {
        // Formatear la salida y llamar al método
        System.out.println("Elementos");
        ListarElementosOrdenados(raiz);
        System.out.println();
    } //Cierro método ListarElementosOrdenados()

    // Este es un método público para llamar a ListarNiveles
    public void ListarNiveles() {
        // Formatea la salida y llama al método.
        System.out.println("Tree");
        ListarNiveles(raiz);
    } // ListarNiveles() finaliza

    // Este método devuelve el número de datos almacenados en el árbol.
    public int numeroDeElemen() {

        return cant_nodos;
    } // numeroDeElemen() finaliza


    
} // finaliza la definición de la clase Arbol23 

