package org.github.tarolas.travian.application.gui.login

import org.github.tarolas.travian.application.gui.main.MainView
import org.github.tarolas.travian.application.utils.SpringContext
import org.github.tarolas.travian.engine.TravianEngineBuilder
import org.github.tarolas.travian.engine.entities.LoginParams
import org.springframework.stereotype.Component

/**
 * @author tiago.ribeiro
 */
@Component
class LoginFormController : SpringContext() {

    fun login(username: String, password: String, server: String) {
        val engine  = TravianEngineBuilder.getEngine(context, LoginParams(username, password, server))
        val mainView = getBean<MainView>()
        mainView.engine = engine
        getBean<LoginFormView>().replaceWith(mainView)
    }

}