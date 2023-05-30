package jmunoz_jzarta_lab_8;

/**
 *
 * @author James - Zarta
 */
public class Camino {

    private String camino;
    private int costo;

    public Camino(String camino, int costo) {
        this.camino = camino;
        this.costo = costo;
    }

    public void setCamino(String Camino) {
        this.camino = Camino;
    }

    public String getCamino() {
        return this.camino;
    }

    public void setCosto(int Costo) {
        this.costo = costo;
    }

    public int getCosto() {
        return this.costo;
    }

}
