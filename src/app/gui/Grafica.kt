package app.gui

import lib.sRAD.gui.component.Resource.DTII2
import lib.sRAD.gui.component.Resource.wp2
import lib.sRAD.gui.sComponent.SPanel
import lib.sRAD.gui.sComponent.SScrollPane
import java.awt.Graphics

object Grafica: SScrollPane(394, 96, 764, 550) {

    private val lineas = mutableListOf<MutableList<Int>>()//coordenadas de las lineas por dibujar

    private val pInterno = object: SPanel(0, 0, 706, 610) {
        override fun paint(g: Graphics?) {
            super.paint(g)
            g!!.color = wp2

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

    }

}