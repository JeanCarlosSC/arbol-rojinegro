package app.gui

import app.logic.Arbol
import app.logic.Nodo
import app.logic.Rojinegros.ROJO
import lib.sRAD.gui.component.Resource.*
import lib.sRAD.gui.sComponent.SLabel
import lib.sRAD.gui.sComponent.SPanel
import lib.sRAD.gui.sComponent.SScrollPane
import java.awt.Graphics

object Grafica: SScrollPane(394, 96, 764, 550) {

    private val lineas = mutableListOf<MutableList<Int>>()//coordenadas de las lineas por dibujar

    private val pInterno = object: SPanel(0, 0, 706, 610) {
        override fun paint(g: Graphics?) {
            super.paint(g)
            g!!.color = darkWhite

            if(lineas.isNotEmpty()){
                for (i in lineas) {
                    g.drawLine(i[0], i[1], i[2], i[3])
                }
            }
        }
    }//Panel contenedor de la gráfica

    init {
        pInterno.background = DTII2
        pInterno.border = null
        add(pInterno)
        setViewportView(pInterno)
    }

    fun actualizar() {
        Arbol.organizar()
        pInterno.removeAll()
        lineas.clear()
        pInterno.setSize(706, 550)
        if(Arbol.isNotEmpty()) {
            dibujar(Arbol.raiz.der)
        }
        pInterno.repaint()
    }

    private fun dibujar(nodo: Nodo?) {
        if(nodo!=null) {
            //modifica tamaño del panel
            if(nodo.x+100>pInterno.width) {
                pInterno.setSize(nodo.x + 100, pInterno.height)
            }
            if(nodo.y+100>pInterno.height) {
                pInterno.setSize(pInterno.width, nodo.y + 100)
            }

            //dibuja nodo
            val panel  = SPanel(nodo.x, nodo.y, 64, 32,
                if(nodo.color == ROJO) ta4 else black,
                if(nodo.color == ROJO) ta7Border else grayBorder
            )

            val lValor = SLabel(2, 2, 58, 30, nodo.llave.toString())
            lValor.horizontalAlignment = SLabel.CENTER
            lValor.foreground = white
            panel.add(lValor)

            pInterno.add(panel)

            //guarda lineas
            if(nodo.izq != null && nodo.izq.llave != Arbol.raiz.llave)
                lineas.add(mutableListOf(nodo.x+22, nodo.y+32, nodo.izq!!.x+32, nodo.izq!!.y))

            if(nodo.der != null && nodo.der.llave != Arbol.raiz.llave)
                lineas.add(mutableListOf(nodo.x+42, nodo.y+32, nodo.der!!.x+32, nodo.der!!.y))

            //dibuja nodos hijos
            if (nodo.izq.llave != Arbol.raiz.llave)
                dibujar(nodo.izq)
            if (nodo.der.llave != Arbol.raiz.llave)
                dibujar(nodo.der)
        }
    }

}