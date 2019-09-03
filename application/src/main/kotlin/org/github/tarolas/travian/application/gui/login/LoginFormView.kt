package org.github.tarolas.travian.application.gui.login

import javafx.application.Platform
import javafx.geometry.Pos
import javafx.scene.layout.BorderPane
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.github.tarolas.travian.application.gui.login.LoginFormConstants.TAB_NAME
import org.github.tarolas.travian.engine.TravianEngineBuilder
import org.github.tarolas.travian.engine.entities.LoginParams
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import tornadofx.*
import tornadofx.FX.Companion.messages

@Component
class LoginFormView(val model: LoginFormModel) : BorderPane(), ApplicationContextAware {

    private lateinit var springContext: ApplicationContext

    override fun setApplicationContext(applicationContext: ApplicationContext) {
        springContext = applicationContext
    }

    private val log = LoggerFactory.getLogger(LoginFormView::class.java)

    init {
        log.debug("Initialized [{}]", javaClass.simpleName)
        Platform.runLater {
            userData = TAB_NAME
            layoutForm()
        }
    }

    protected fun layoutForm() {
        center = form {
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
                            val engine  = TravianEngineBuilder.getEngine(springContext, LoginParams(model.username.get(), model.password.get(), model.server.get()))
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
