package org.github.tarolas.travian.application.gui.login

import javafx.application.Platform
import javafx.geometry.Pos
import javafx.scene.Parent
import javafx.scene.layout.BorderPane
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.github.tarolas.travian.application.gui.login.LoginFormConstants.TAB_NAME
import org.github.tarolas.travian.application.gui.main.MainView
import org.github.tarolas.travian.engine.TravianEngineBuilder
import org.github.tarolas.travian.engine.entities.LoginParams
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import tornadofx.*
import tornadofx.FX.Companion.messages
import java.util.*

@Component
class LoginFormView(
        val model: LoginFormModel,
        val controller: LoginFormController
) : View() {

    //private val log = LoggerFactory.getLogger(LoginFormView::class.java)

    override final val root = Form()

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
                    button(messages["button.label"]) {
                        //                        graphic = GlyphFactory.create(Glyph.SAVE)
                        action {
                            log.info("logIn: username:${model.username.get()}, password:${model.password.get()}, server: ${model.server.get()}")
                            controller.login(model.username.get(), model.password.get(), model.server.get())
                        }
                        // Save button is disabled until every field has a value
                        disableProperty().bind(model.username.isNull.or(model.password.isNull
                                .or(model.server.isNull)))
                    }
                }
            }
        }
    }

//    protected fun layoutForm() {
//        center = form {
//            alignment = Pos.CENTER
//            fieldset(messages["login.title"]) {
//                vbox {
//                    field(messages["username.label"]) {
//                        textfield().bind(model.username)
//                    }
//                    field(messages["password.label"]) {
//                        passwordfield().bind(model.password)
//                    }
//                    field(messages["server.label"]) {
//                        textfield().bind(model.server)
//                    }
//                    button(messages["button.label"]) {
////                        graphic = GlyphFactory.create(Glyph.SAVE)
//                        action {
//                            log.info("logIn: username:${model.username.get()}, password:${model.password.get()}, server: ${model.server.get()}")
//                            controller.login(model.username.get(), model.password.get(), model.server.get())
//                        }
//                        // Save button is disabled until every field has a value
//                        disableProperty().bind(model.username.isNull.or(model.password.isNull
//                                .or(model.server.isNull)))
//                    }
//                }
//            }
//        }
//    }
}
