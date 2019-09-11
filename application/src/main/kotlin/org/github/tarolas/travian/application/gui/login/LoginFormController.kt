package org.github.tarolas.travian.application.gui.login

import org.github.tarolas.travian.application.gui.main.MainView
import org.github.tarolas.travian.application.utils.SpringContext
import org.github.tarolas.travian.engine.TravianEngineBuilder
import org.github.tarolas.travian.engine.entities.LoginParams
import org.github.tarolas.travian.engine.entities.ProxyParams
import org.springframework.stereotype.Component

/**
 * @author tiago.ribeiro
 */
@Component
class LoginFormController : SpringContext() {

    suspend fun login(model: LoginFormModel) {

        val engine  = TravianEngineBuilder.getEngine(context,
                LoginParams(
                        model.username.get(),
                        model.password.get(),
                        model.server.get(),
                        model.proxyHost.get()?.let {
                            ProxyParams(it, model.proxyPort.get())
                        }
                )
        )
        val mainView = getBean<MainView>()
        mainView.engine = engine
        getBean<LoginFormView>().replaceWith(mainView)
    }

}