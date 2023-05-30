package jmunoz_jzarta_lab4;


/*
 * @author Juan P Martinez
 */
public class ArbolBin {

    private Nodo raiz;

    public ArbolBin() {
        this.raiz = null;
    }

    public void setRaiz(Nodo nod) {
        this.raiz = nod;
    }

    public Nodo getRaiz() {
        return raiz;
    }

    public void insertar(int d) {
        Nodo nuevo = new Nodo(d);

        if (raiz == null) {
            raiz = nuevo;
        } else {
            Nodo padre = null;
            Nodo aux;
            aux = raiz;
            while (aux != null) {
                padre = aux;
                if (d < aux.getDato()) {
                    aux = aux.getIzquierda();
                } else {
                    aux = aux.getDerecha();
                }
            }
            if (d < padre.getDato()) {
                padre.setIzquierda(nuevo);
            } else {
                padre.setDerecha(nuevo);
            }
        }
        System.out.println(d + " insertado ");

    } //fin insertar

    public void imprimirPreorden(Nodo r) {
        if (r != null) {
            System.out.print(r.getDato() + " ");
            imprimirPreorden(r.getIzquierda());
            imprimirPreorden(r.getDerecha());
        }

    }

    /*
    El método recursivo void imprimirPreorden(Nodo aux) lo primero que verifica con un if si aux está apuntando a un nodo (esto es verdad si aux es distinto a null), en caso afirmativo ingresa al bloque del if y realiza:
     - Visitar la raiz.
     - Recorrer el subárbol izquierdo en pre-orden.
     - Recorrer el subárbol derecho en pre-orden.
     */
    public void imprimirInorden(Nodo r) {
        if (r != null) {
            imprimirInorden(r.getIzquierda());
            System.out.print(r.getDato() + " ");
            imprimirInorden(r.getDerecha());
        }

    }

    public void imprimirPostorden(Nodo r) {
        if (r != null) {
            imprimirInorden(r.getIzquierda());
            imprimirInorden(r.getDerecha());
            System.out.print(r.getDato() + " ");
        }

    }

    public void buscar(int clave, Nodo p) {
        if (p == null) {
            System.out.println(clave + " No encontrado ");
        } else if (clave > p.getDato()) {
            buscar(clave, p.getDerecha());
        } else if (clave < p.getDato()) {
            buscar(clave, p.getIzquierda());
        } else if (clave == p.getDato()) {
            System.out.println(clave + " Encontrado");
        }
    }

    public void eliminar(int clave, Nodo p, Nodo nodo_anterior, boolean izquierda, boolean derecha) {
        if (p == null) {
            System.out.println(clave + " No encontrado ");
        } else if (this.getRaiz().getDato() == clave) {// borrar nodo raiz
            Nodo nodo_temp = p.getIzquierda();
            while (nodo_temp.getDerecha() != null) {
                nodo_temp = nodo_temp.getDerecha();
            }
            this.eliminar(nodo_temp.getDato(), this.getRaiz().getIzquierda(), null, false, false);

            this.getRaiz().setDato(nodo_temp.getDato());

        } else if (clave > p.getDato()) {
            eliminar(clave, p.getDerecha(), p, false, true);
        } else if (clave < p.getDato()) {
            eliminar(clave, p.getIzquierda(), p, true, false);
        } else if (clave == p.getDato()) {

            if (p.getDerecha() != null && p.getIzquierda() != null) {
                Nodo nodo_temp = p.getIzquierda();
                while (nodo_temp.getDerecha() != null) {
                    nodo_temp = nodo_temp.getDerecha();
                }
                this.eliminar(nodo_temp.getDato(), p, null, false,true);

                p.setDato(nodo_temp.getDato());
            } else {
                if (p.getDerecha() == null && p.getIzquierda() == null) {// para nodo hoja
                    if (izquierda) {
                        nodo_anterior.setIzquierda(null);
                    }
                    if (derecha) {
                        nodo_anterior.setDerecha(null);
                    }
                }
                if (p.getIzquierda() != null) { // SI TIENE HIJO A LA IZQUIERDA
                    if (izquierda) {
                        nodo_anterior.setIzquierda(p.getIzquierda());
                    }
                    if (derecha) {
                        nodo_anterior.setDerecha(p.getIzquierda());
                    }
                }
                if (p.getDerecha() != null) { //si tiene hijo a la derecha
                    if (izquierda) {
                        nodo_anterior.setIzquierda(p.getDerecha());
                    }
                    if (derecha) {
                        nodo_anterior.setDerecha(p.getDerecha());
                    }
                }
            }

            // System.out.println(clave + " Encontrado");
        }

    }

}

