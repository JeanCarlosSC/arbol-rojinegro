package app.gui

import app.logic.Arbol
import lib.sRAD.gui.component.VentanaEmergente
import lib.sRAD.gui.sComponent.SButton
import lib.sRAD.gui.sComponent.SLabel
import lib.sRAD.gui.sComponent.SPanel
import lib.sRAD.gui.sComponent.STextField
import lib.sRAD.logic.Extension.isInt
import javax.swing.JOptionPane

object ToolPane: SPanel(142, 96, 220, 550) {

    init {
        val btAdd = SButton(0, 0, 220, 32, "Añadir")
        btAdd.addActionListener { abrirVentanaInsertar() }
        add(btAdd)

        val btRemove = SButton(0, 30, 220, 32, "Retirar")
        btRemove.addActionListener { abrirVentanaRetirar() }
        add(btRemove)

        val btSearch = SButton(0, 60, 220, 32, "Consultar")
        btSearch.addActionListener { abrirVentanaConsultar() }
        add(btSearch)

        val btClear = SButton(0, 90, 220, 32, "Inicializar")
        btClear.addActionListener {
            Arbol.inicializar()
            Grafica.actualizar()
        }
        add(btClear)

        val pInOrden = SPanel(0, 120, 220, 430)
        add(pInOrden)
    }

    fun abrirVentanaInsertar() {
        val ventana = VentanaEmergente(App, 500, 120)

        val lText = SLabel(50, 23, 240, 28,"Inserte el codigo")
        ventana.add(lText)

        val taNum = STextField(190, 21, 140, 32)
        ventana.add(taNum)

        val lText1 = SLabel(50, 64, 240, 28,"Inserte el nombre")
        ventana.add(lText1)

        val taName = STextField(190, 60, 140, 32)
        taName.addActionListener {
            if(isInt(taNum.text) && taName.text.isNotEmpty()) {
                Arbol.insertar(taNum.text.toInt(), taName.text)
                Grafica.actualizar()
            }
            else {
                JOptionPane.showMessageDialog(null, "Ingrese valores válidos", "Error", JOptionPane.ERROR_MESSAGE)
            }
            ventana.cerrar()
        }
        ventana.add(taName)

        val btCancelar = SButton(355, 21, 100, 32, "Cancelar")
        btCancelar.addActionListener {
            ventana.cerrar()
        }
        ventana.add(btCancelar)

        ventana.lanzar()
    }

    fun abrirVentanaRetirar() {

    }

    fun abrirVentanaConsultar() {

    }

}