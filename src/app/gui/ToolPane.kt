package app.gui

import lib.sRAD.gui.sComponent.SButton
import lib.sRAD.gui.sComponent.SPanel

object ToolPane: SPanel(142, 96, 220, 550) {

    init {
        val btAdd = SButton(0, 0, 220, 32, "Añadir")
        add(btAdd)

        val btRemove = SButton(0, 30, 220, 32, "Retirar")
        add(btRemove)

        val btSearch = SButton(0, 60, 220, 32, "Consultar")
        add(btSearch)

        val pInOrden = SPanel(0, 90, 220, 460)
        add(pInOrden)
    }

}