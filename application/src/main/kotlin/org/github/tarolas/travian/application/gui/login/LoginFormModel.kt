package org.github.tarolas.travian.application.gui.login

import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import org.springframework.stereotype.Component

@Component
class LoginFormModel {
    val username = SimpleStringProperty(this, "username", null)
    val password = SimpleStringProperty(this, "password", null)
    val server = SimpleStringProperty(this, "server", null)
    val proxyHost = SimpleStringProperty(this, "proxyHost", null)
    val proxyPort = SimpleObjectProperty<Int>(this, "proxyPort", null)
}
