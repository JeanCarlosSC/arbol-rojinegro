package app.logic

object Arbol: Rojinegros() {
    private val nombres = mutableListOf<MutableList<String>>()

    init {
        inicializar()
    }

    fun insertar(codigo: Int, nombre: String) {
        if(super.insertar(codigo))
            nombres.add(mutableListOf("$codigo", nombre))
    }

}