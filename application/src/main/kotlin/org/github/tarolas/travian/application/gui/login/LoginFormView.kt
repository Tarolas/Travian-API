package org.github.tarolas.travian.application.gui.login

import javafx.geometry.Pos
import org.github.tarolas.travian.application.utils.runAsyncSuspend
import org.springframework.stereotype.Component
import tornadofx.*
import java.util.*

@Component
class LoginFormView(
        val model: LoginFormModel,
        val controller: LoginFormController
) : View() {

    final override val root = Form()

    init {
        messages = ResourceBundle.getBundle("views/LoginFormView", FX.locale)
        with(root) {
            alignment = Pos.CENTER
            fieldset(messages["login.title"]) {
                vbox {
                    field(messages["username.label"]) {
                        textfield().bind(model.username)
                    }
                    field(messages["password.label"]) {
                        passwordfield().bind(model.password)
                    }
                    field(messages["server.label"]) {
                        textfield().bind(model.server)
                    }
                    fieldset("Proxy: (optional)") {
                        field("Host:") {
                            textfield().bind(model.proxyHost)
                        }
                        field("Port:") {
                            textfield().bind(model.proxyPort)
                        }
                    }
                    button(messages["button.label"]) {
                        //                        graphic = GlyphFactory.create(Glyph.SAVE)
                        action {
                            log.info("logIn: username:${model.username.get()}, password:${model.password.get()}, server: ${model.server.get()}")
                            runAsyncSuspend { controller.login(model) }
                        }
                        // Save button is disabled until every field has a value
                        disableProperty().bind(model.username.isNull.or(model.password.isNull
                                .or(model.server.isNull)))
                    }
                }
            }
        }
    }
}


