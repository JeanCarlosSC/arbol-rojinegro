import app.logic.Rojinegros;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {
        Rojinegros arbol = new Rojinegros();
        InputStreamReader entrada = new InputStreamReader(System.in);
        BufferedReader teclado = new BufferedReader(entrada);
        arbol.inicializar();
        String aux;
        int v;
        System.out.println("INSERTAR NODOS");
        System.out.println("Digite Numero. 9999 para terminar");
        try {
            aux = teclado.readLine();
            v = Integer.parseInt(aux);
            while ( v != 9999) {
                arbol.insertar(v);
                arbol.inorden( arbol.raiz() );
                System.out.println();
                arbol.preorden( arbol.raiz() );
                System.out.println();
                System.out.println("--------------------------");
                System.out.println("Digite Numero. 9999 para terminar");
                aux = teclado.readLine();
                v = Integer.parseInt(aux);
            }
        }
        catch (IOException e1) {
            System.out.println("Error al abrir el teclado.");
        }
        // retiros
        System.out.println("RETIRAR NODOS");
        System.out.println("Digite Numero. 9999 para terminar");
        try {
            aux = teclado.readLine();
            v = Integer.parseInt(aux);
            while ( v != 9999) {
                arbol.eliminar(arbol.raiz(), v);
                arbol.inorden( arbol.raiz() );
                System.out.println();
                arbol.preorden( arbol.raiz() );
                System.out.println();
                System.out.println("--------------------------");
                System.out.println("Digite Numero. 9999 para terminar");
                aux = teclado.readLine();
                v = Integer.parseInt(aux);
            }
        }
        catch (IOException e1) {
            System.out.println("Error al abrir el teclado.");
        }
    }

}
