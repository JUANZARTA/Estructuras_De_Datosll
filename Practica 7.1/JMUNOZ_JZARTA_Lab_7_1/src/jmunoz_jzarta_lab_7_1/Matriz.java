package jmunoz_jzarta_lab_7_1;

/**
 *
 * @author James
 */


public class Matriz {

    private int n;
    private int[][] matriz;

    public Matriz(int n) {
        this.n = n;
        this.matriz = new int[this.n][this.n];
        //se inicializa matriz en 0
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                this.matriz[i][j] = 0;
            }
        }
    }

    public void agregar(int i, int j) {
        this.matriz[i][j] += 1;
    }

    public void remover(int i, int j) {
        if (this.matriz[i][j] > 0) {
            this.matriz[i][j] -= 1;
        }
    }

    public void imprimir(char[] indices) {
        System.out.print("   ");
        for (int i = 0; i < indices.length; i++) {
            System.out.print(indices[i] + "  ");
        }
        System.out.println();
        for (int i = 0; i < n; i++) {
            System.out.print(indices[i] + "  ");
            for (int j = 0; j < n; j++) {
                System.out.print(this.matriz[i][j] + "  ");
            }
            System.out.println();
        }
    }
}
