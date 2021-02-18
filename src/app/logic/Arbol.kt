package app.logic

object Arbol: Rojinegros() {
    private val nombres = mutableListOf<MutableList<String>>()
    private var minimo = 0

    init {
        inicializar()
    }

    fun insertar(codigo: Int, nombre: String) {
        if(super.insertar(codigo))
            nombres.add(mutableListOf("$codigo", nombre))
    }

    fun isNotEmpty(): Boolean {
        return nombres.isNotEmpty()
    }

    override fun inicializar() {
        super.inicializar()
        nombres.clear()
    }

    fun organizar() {
        if (nombres.isEmpty())
            return
        minimo = 32
        reestablecerCoordenadas(raiz.der)
        moverNodo(raiz.der, 32, 32)
        moverHaciaDerecha(raiz.der)
    }

    fun inOrden(nodo: Nodo): String {
        if(nodo != null && nodo.llave != raiz.llave) {
            var nombre = "";
            for (i in nombres) {
                if (i[0] == nodo.llave.toString()) {
                    nombre = i[1]
                    break;
                }
            }
            return inOrden(nodo.izq)+"%4d".format(nodo.llave)+" "+nombre+"\n"+inOrden(nodo.der);
        }
        return "";
    }

    override fun eliminar(arbol: Nodo, llave: Int) {
        super.eliminar(arbol, llave)
        for (i in nombres.indices) {
            if (nombres[i][0] == llave.toString()) {
                nombres.removeAt(i)
                break;
            }
        }
    }

    private fun reestablecerCoordenadas(nodo: Nodo?) {
        if(nodo == null)
            return
        nodo.x = 0
        nodo.y = 0
        if (nodo.izq.llave != raiz.llave)
            reestablecerCoordenadas(nodo.izq)
        if (nodo.der.llave != raiz.llave)
            reestablecerCoordenadas(nodo.der)
    }

    private fun moverNodo(nodo: Nodo?, x: Int, y: Int) {
        if (nodo == null) {
            return
        }
        if (x < minimo) {
            minimo = x
        }
        if (isNotPosFree(raiz!!, x, y)) {
            var pariente = obtenerPariente(raiz!!, nodo)
            val xi = pariente!!.x
            val yi = pariente.y+60
            while (pariente != null) {
                pariente.x+=45
                pariente = obtenerPariente(raiz!!, pariente)
            }
            moverNodo(nodo, xi, yi)
        }
        else {
            nodo.x = x
            nodo.y = y
            if (nodo.izq != null && nodo.izq.llave != raiz.llave)
                moverNodo(nodo.izq, nodo.x - 45, nodo.y + 60)
            if (nodo.der != null && nodo.der.llave != raiz.llave) {
                moverNodo(nodo.der, nodo.x + 45, nodo.y + 60)
                if (nodo.izq != null && nodo.izq.llave != raiz.llave) {
                    nodo.x = (nodo.izq!!.x + nodo.der!!.x)/2
                }
            }
        }
    }

    private fun obtenerPariente(raiz: Nodo?, nodo: Nodo): Nodo? {
        //si la raiz es nula
        if (raiz == null) {
            return null
        }
        //si el que se busca es la raiz
        if (raiz.llave == nodo.llave) {
            return null
        }
        //si la raiz no tiene hijos
        if (raiz.izq.llave == raiz.llave && raiz.der.llave == raiz.llave) {
            return null
        }
        //si la raiz dada es pariente del nodo
        if (raiz.izq != null && raiz.izq!!.llave == nodo.llave && raiz.izq!!.llave != raiz.llave ||
            raiz.der != null && raiz.der!!.llave == nodo.llave && raiz.der!!.llave != raiz.llave) {
            return raiz
        }
        //else, busca en los sub-árboles
        if (raiz.izq != null && raiz.izq.llave != raiz.llave && obtenerPariente(raiz.izq!!, nodo) != null) {
            return obtenerPariente(raiz.izq, nodo)
        }
        return obtenerPariente(raiz.der, nodo)
    }

    private fun isNotPosFree(nodo: Nodo?, x: Int, y: Int): Boolean {
        if (nodo == null)
            return false
        if ((x >= nodo.x -60 && x <=nodo.x +60) && nodo.y == y)
            return true

        return isNotPosFree(if (nodo.izq.llave != raiz.llave) nodo.izq else null, x, y) ||
                isNotPosFree(if (nodo.der.llave != raiz.llave) nodo.der else null, x, y)
    }

    private fun moverHaciaDerecha(nodo: Nodo?) {
        if(nodo != null) {
            nodo.x += (minimo-32)*-1
            if (nodo.izq.llave != nodo.llave)
                moverHaciaDerecha(nodo.izq)
            if (nodo.der.llave != nodo.llave)
                moverHaciaDerecha(nodo.der)
        }
    }

}