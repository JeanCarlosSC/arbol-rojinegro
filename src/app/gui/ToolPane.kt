package app.gui

import app.logic.Arbol
import lib.sRAD.gui.component.Resource.fontTitle
import lib.sRAD.gui.component.VentanaEmergente
import lib.sRAD.gui.sComponent.*
import lib.sRAD.logic.Extension.isInt
import javax.swing.JOptionPane

object ToolPane: SPanel(142, 96, 220, 550) {

    val pInOrden: SPanel

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

        pInOrden = SPanel(0, 120, 220, 430)
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
            if(isInt(taNum.text) && taName.text.isNotEmpty() && taNum.text.toInt()>0) {
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
        val ventana = VentanaEmergente(App, 500, 80)

        val lText = SLabel(30, 23, 240, 28,"Remueva un código ")
        ventana.add(lText)

        val taNum = STextField(235, 21, 100, 32)
        taNum.addActionListener {
            if(isInt(taNum.text) && taNum.text.toInt()>0) {
                Arbol.eliminar(Arbol.raiz, taNum.text.toInt())
                Grafica.actualizar()
            }
            else {
                JOptionPane.showMessageDialog(null, "Ingrese un número entero", "Error", JOptionPane.ERROR_MESSAGE)
            }
            ventana.cerrar()
        }
        ventana.add(taNum)

        val btCancelar = SButton(355, 21, 100, 32, "Cancelar")
        btCancelar.addActionListener {
            ventana.cerrar()
        }
        ventana.add(btCancelar)

        ventana.lanzar()
    }

    fun abrirVentanaConsultar() {

    }

    fun inOrden() {
        pInOrden.removeAll()
        if (Arbol.isNotEmpty()) {
            val lInOrden = SLabel(32, 22, 150, 32, "In-orden:", fontTitle)
            pInOrden.add(lInOrden)

            val lList = STextArea(32, 54, 150, 370, Arbol.inOrden(Arbol.raiz.der))
            pInOrden.add(lList)
        }
        pInOrden.repaint()
    }

}