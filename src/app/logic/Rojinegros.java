package app.logic;

public class Rojinegros {

    Nodo raiz;
    Nodo z;
    final int NEGRO = 0;
    final int ROJO = 1;

    public Nodo raiz() {
        return raiz;
    }

    public void inicializar() {
        z = new Nodo(0, NEGRO);
        z.izq = z;
        z.der = z;
        raiz = new Nodo(0, NEGRO);
        raiz.izq = z;
        raiz.der = z;
    }

    Nodo rotar(int v, Nodo p) {
        Nodo c;
        Nodo gc;
        if (v < p.llave) {
            c = p.izq;
        } else c = p.der;

        if (v < c.llave) {
            gc = c.izq;
            c.izq = gc.der;
            gc.der = c;
        } else {
            gc = c.der;
            c.der = gc.izq;
            gc.izq = c;
        }

        if (v < p.llave) {
            p.izq = gc;
        } else p.der = gc;
        return gc;
    }

    Nodo dividir(int v, Nodo gf, Nodo g, Nodo p, Nodo x) {
        x.color = ROJO;
        x.izq.color = NEGRO;
        x.der.color = NEGRO;
        if (p.color == ROJO) {
            g.color = ROJO;
            if (v < g.llave != v < p.llave) {
                System.out.println("sentido contrario-" + g.llave);
                p = rotar(v, g);
            }
            System.out.println("mismo sentido-" + gf.llave);
            x = rotar(v, gf);
            x.color = NEGRO;
        }
        raiz.der.color = NEGRO;
        return x;
    }

    public void insertar(int v) {
        Nodo gf, g, p, x;
        x = raiz;
        p = x;
        g = x;
        do {
            if (v < x.llave) {
                gf = g;
                g = p;
                p = x;
                x = x.izq;
            } else if (v > x.llave) {
                gf = g;
                g = p;
                p = x;
                x = x.der;
            } else {
                System.out.println(" La llave ya existe.." + v);
                return;
            }
            if (x.izq.color == ROJO && x.der.color == ROJO) {
                x = dividir(v, gf, g, p, x);
            }
        } while (x != z);
        x = new Nodo(v); // color sin asignar todavia
        x.llave = v;
        x.izq = x.der = z;
        if (v < p.llave) {
            p.izq = x;
        } else p.der = x;
        x = dividir(v, gf, g, p, x);
    }

    public void inorden(Nodo p) {
        if (p != z) {
            inorden(p.izq);
            if (p.color == ROJO)
                System.out.print(" " + p.llave + "" + "R");
            else System.out.print(" " + p.llave + "" + "N");
            inorden(p.der);
        }
    }

    public void preorden(Nodo p) {
        if (p != z) {
            if (p.color == ROJO)
                System.out.print(" " + p.llave + "" + "R");
            else System.out.print(" " + p.llave + "" + "N");
            preorden(p.izq);
            preorden(p.der);
        }
    }

    boolean colorRojo(Nodo raiz) {
        return raiz != z && raiz.color == ROJO;
    }

    Nodo simpleRotacion(Nodo raiz, int dir) {
        Nodo temp;
        if (dir == 1) {
            System.out.println("Simple Rotacion a la der.");
            temp = raiz.izq;
            raiz.izq = temp.der;
            temp.der = raiz;
        } else {
            System.out.println("Simple Rotacion a la izq.");
            temp = raiz.der;
            raiz.der = temp.izq;
            temp.izq = raiz;
        }
        raiz.color = ROJO;
        temp.color = NEGRO;
        return temp;
    }

    Nodo dobleRotacion(Nodo raiz, int dir) {
        if (dir == 1) {
            raiz.izq = simpleRotacion(raiz.izq, 0);
        } else {
            raiz.der = simpleRotacion(raiz.der, 1);
        }
        return simpleRotacion(raiz, dir);
    }

    public void eliminar(Nodo arbol, int llave) {
        if (arbol.der != z) {
            Nodo cabeza = new Nodo(0);
            cabeza.color = NEGRO;
            cabeza.izq = z;
            cabeza.der = z;
            Nodo temp, q, p, g;
            Nodo t, f = z;
            int dir = 1;
            q = cabeza;
            g = p = z;
            q.der = arbol.der;
            t = q.der;
            while (t != z) {
                int anterior = dir;
                Nodo w, v;
                g = p;
                p = q;
                if (anterior == 0)
                    v = p.der;
                else v = p.izq;
                if (dir == 0)
                    q = q.izq;
                else q = q.der;
                // actualiza dir
                if (q.llave < llave)
                    dir = 1;
                else dir = 0;
                if (q.llave == llave)
                    f = q;
                if (dir == 0) {
                    t = q.izq;
                    w = q.der;
                } else {
                    t = q.der;
                    w = q.izq;
                }
                if (!colorRojo(q) && !colorRojo(t)) {
                    if (colorRojo(w)) {
                        temp = simpleRotacion(q, dir);
                        if (anterior == 0)
                            p.izq = temp;
                        else p.der = temp;
                        p = temp;
                    } else if (!colorRojo(w)) {
                        Nodo s = v;
                        Nodo r1, v1;
                        if (anterior == 0) {
                            r1 = s.izq;
                            v1 = s.der;
                        } else {
                            r1 = s.der;
                            v1 = s.izq;
                        }
                        if (s != z) {
                            if (!colorRojo(v1)
                                    && !colorRojo(r1)) {
                                p.color = NEGRO;
                                s.color = ROJO;
                                q.color = ROJO;
                            } else {
                                int dir2;
                                Nodo r2;
                                if (g.der == p)
                                    dir2 = 1;
                                else dir2 = 0;
                                if (dir2 == 0)
                                    r2 = g.izq;
                                else r2 = g.der;
                                if (colorRojo(r1)) {
                                    if (dir2 == 0) {
                                        System.out.println("Doble Rotacion a la izq.");
                                        g.izq = dobleRotacion(p, anterior);
                                    } else {
                                        System.out.println("Doble Rotacion a la der.");
                                        g.der = dobleRotacion(p, anterior);
                                    }
                                } else if (colorRojo(v1)) {
                                    if (dir2 == 0)
                                        g.izq = simpleRotacion(p, anterior);
                                    else g.der = simpleRotacion(p, anterior);
                                }
                                if (dir2 == 0)
                                    r2 = g.izq;
                                else r2 = g.der;
                                q.color = r2.color = ROJO;
                                r2.izq.color = NEGRO;
                                r2.der.color = NEGRO;
                            }
                        } /// if ( s != z )
                    } /// if ( !colorRojo ( w ) )
                } /// if ( !colorRojo( q ) && !colorRojo ( t ) )
            } /// while ( terminar != z )
            if (f != z) {
                Nodo h;
                f.llave = q.llave;
                if (q.izq == z)
                    h = q.der;
                else h = q.izq;
                if (p.der == q)
                    p.der = h;
                else p.izq = h;
                q = null;
            } else {
                System.out.println(" ! No existe la llave " + llave);
            }
            arbol.der = cabeza.der;
            cabeza = null;
            if (arbol.der != z)
                arbol.der.color = NEGRO;
        }
    }

}
