package app.logic;

public class Nodo {

    public int llave;
    public int color;
    public Nodo izq;
    public Nodo der;
    public int x=0;
    public int y=0;

    Nodo (int llave, int color) {
        this.llave = llave;
        this.color = color;
    }

    Nodo (int llave) {
        this.llave = llave;
    }

}
