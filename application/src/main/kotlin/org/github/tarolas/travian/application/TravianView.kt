package org.github.tarolas.travian.application

import javafx.scene.layout.BorderPane
import org.github.tarolas.travian.application.gui.login.LoginFormView
import org.springframework.stereotype.Component

@Component
class TravianView(val loginFormView: LoginFormView) : BorderPane() {
}