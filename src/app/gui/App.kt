package app.gui

import lib.sRAD.gui.sComponent.SFrame
import lib.sRAD.gui.sComponent.SMenuBar
import javax.swing.JMenu
import javax.swing.JMenuItem
import javax.swing.JOptionPane

object App: SFrame() {

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
        about.addActionListener {
            JOptionPane.showMessageDialog(null, "" +
                    "This software has been developed by Jean Carlos Santoya Cabrera." +
                    "\nReferences:" +
                    "\n - https://porcomputador.com/", "About", JOptionPane.INFORMATION_MESSAGE
            )
        }
        help.add(about)

        add(menuBar)
    }

}