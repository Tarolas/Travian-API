package org.github.tarolas.travian.application

import javafx.application.Platform
import javafx.scene.control.Tab
import javafx.scene.layout.BorderPane
import org.github.tarolas.travian.application.gui.login.LoginFormView
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class TravianView(val loginFormView: LoginFormView) : BorderPane() {

    private val log = LoggerFactory.getLogger(TravianView::class.java)

    init {
        Platform.runLater {
            log.info("Initializing  [{}] ", javaClass.simpleName)
            center = loginFormView
        }
    }

}