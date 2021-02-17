package app.gui

import lib.sRAD.gui.sComponent.SFrame
import lib.sRAD.gui.sComponent.SMenuBar
import javax.swing.JMenu
import javax.swing.JMenuItem

class App: SFrame() {

    init {
        addMenuBar()

        add(ToolPane)
        add(Grafica)

        setMainBar("Árboles Rojinegros")
        setProperties()
    }

    private fun addMenuBar() {
        val menuBar = SMenuBar()

        val help = JMenu("Help")
        menuBar.add(help)

        val about = JMenuItem("About")
        help.add(about)

        add(menuBar)
    }

}