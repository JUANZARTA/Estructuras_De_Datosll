package jmunoz_jzarta_lab5;

public class ArbolAvl {

    private NodoAvl raiz;

    public ArbolAvl() {
        this.raiz = null;
    }

    public void setRaiz(NodoAvl nod) {
        this.raiz = nod;
    }

    public NodoAvl getRaiz() {
        return this.raiz;
    }

    public void imprimirPreorden(NodoAvl r) {
        if (r != null) {
            System.out.print(r.getDato() + " ");
            imprimirPreorden(r.getIzquierda());
            imprimirPreorden(r.getDerecha());
        }
    }

    public void imprimirInorden(NodoAvl r) {
        if (r != null) {
            imprimirInorden(r.getIzquierda());
            System.out.print(r.getDato() + " ");
            imprimirInorden(r.getDerecha());
        }
    }

    public void imprimirPostorden(NodoAvl r) {
        if (r != null) {
            imprimirInorden(r.getIzquierda());
            imprimirInorden(r.getDerecha());
            System.out.print(r.getDato() + " ");
        }

    }

    public void buscar(int clave, NodoAvl p) {
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

    public void insertar(int dato) {
        this.raiz = insertarAVL(this.raiz, dato);
    }

    private NodoAvl insertarAVL(NodoAvl nodoActual, int dato) {
        if (nodoActual == null) {
            return (new NodoAvl(dato, 1));
        }

        if (dato < nodoActual.getDato()) {
            nodoActual.setIzquierda(insertarAVL(nodoActual.getIzquierda(), dato));
        } else if (dato > nodoActual.getDato()) {
            nodoActual.setDerecha(insertarAVL(nodoActual.getDerecha(), dato));
        } else {// Si el dato esta duplicado retorna el mismo nodo
            return nodoActual;
        }

        //Actualizacion de la altura
        nodoActual.setAlt(1 + maximo(getAltura(nodoActual.getIzquierda()), getAltura(nodoActual.getDerecha())));

        // Se obtiene el factor de equilibrio
        int fe = getFactorEquilibrio(nodoActual);

        /////// SE REALIZAN LAS ROTACIONES 
        if (fe > 1 && dato < nodoActual.getIzquierda().getDato()) {////// rotacion simple a la derecha
            return RotarDerecha(nodoActual);
        }
        if (fe < -1 && dato > nodoActual.getDerecha().getDato()) {//////rotacion simple a la izquierda
            return RotarIzquierda(nodoActual);
        }
        if (fe > 1 && dato > nodoActual.getIzquierda().getDato()) { ////// rotacion doble a la derecha
            nodoActual.setIzquierda(RotarIzquierda(nodoActual.getIzquierda()));
            return RotarDerecha(nodoActual);
        }
        if (fe < -1 && dato < nodoActual.getDerecha().getDato()) {  //////rotacion doble a la izquierda
            nodoActual.setDerecha(RotarDerecha(nodoActual.getDerecha()));
            return RotarIzquierda(nodoActual);
        }
        return nodoActual;
    }

    private NodoAvl RotarDerecha(NodoAvl nodoActual) {
        NodoAvl nuevaRaiz = nodoActual.getIzquierda();
        NodoAvl temp = nuevaRaiz.getDerecha();

        // Se realiza la rotacion
        nuevaRaiz.setDerecha(nodoActual);
        nodoActual.setIzquierda(temp);

        // Actualiza alturas
        nodoActual.setAlt(maximo(getAltura(nodoActual.getIzquierda()), getAltura(nodoActual.getDerecha())) + 1);
        nuevaRaiz.setAlt(maximo(getAltura(nuevaRaiz.getIzquierda()), getAltura(nuevaRaiz.getDerecha())) + 1);

        return nuevaRaiz;
    }

    private NodoAvl RotarIzquierda(NodoAvl nodoActual) {
        NodoAvl nuevaRaiz = nodoActual.getDerecha();
        NodoAvl temp = nuevaRaiz.getIzquierda();

        // Se realiza la rotacion
        nuevaRaiz.setIzquierda(nodoActual);
        nodoActual.setDerecha(temp);

        // Actualiza alturas
        nodoActual.setAlt(maximo(getAltura(nodoActual.getIzquierda()), getAltura(nodoActual.getDerecha())) + 1);
        nuevaRaiz.setAlt(maximo(getAltura(nuevaRaiz.getIzquierda()), getAltura(nuevaRaiz.getDerecha())) + 1);

        return nuevaRaiz;
    }

    private int getAltura(NodoAvl nodoActual) {
        if (nodoActual == null) {
            return 0;
        }
        return nodoActual.getAlt();
    }

    private int maximo(int a, int b) {
        return (a > b) ? a : b;
    }

    private int getFactorEquilibrio(NodoAvl nodoActual) {
        if (nodoActual == null) {
            return 0;
        }
        return getAltura(nodoActual.getIzquierda()) - getAltura(nodoActual.getDerecha());
    }

}
