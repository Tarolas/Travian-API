package org.github.tarolas.travian.application.gui.main

import javafx.scene.layout.VBox
import org.github.tarolas.travian.engine.TravianEngineInterface
import org.springframework.stereotype.Component
import tornadofx.*

/**
 * @author tiago.ribeiro
 */
@Component
class MainView : View() {

    lateinit var engine: TravianEngineInterface

    override val root = VBox()

    init {
        with(root) {
            tabpane {
                label("Welcome!")
            }
        }
    }

}