package app.logic;

public class Nodo {

    int llave;
    int color;
    Nodo izq;
    Nodo der;

    Nodo (int llave, int color) {
        this.llave = llave;
        this.color = color;
    }

    Nodo (int llave) {
        this.llave = llave;
    }

}
