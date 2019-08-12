package org.github.tarolas.travian.engine

import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient

class DefaultOperationHandlerDecoratorFactory(
        val client: WebClient
) : OperationHandlerDecoratorFactory, ApplicationContextAware {

    private lateinit var context: ApplicationContext

    override fun setApplicationContext(applicationContext: ApplicationContext) {
        context = applicationContext
    }

    override fun <T : OperationHandler> decorate(operationHandler: OperationHandler, decoratorClazz: Class<T>): T {
        for (ctor in decoratorClazz.constructors) {
            val parametersTypes = ctor.parameterTypes

            val objs = parametersTypes.map {
                when {
                    it.isAssignableFrom(OperationHandler::class.java) -> operationHandler
                    it.isAssignableFrom(WebClient::class.java) -> client
                    else -> context.getBean(it)
                }
            }

            return ctor.newInstance(*objs.toTypedArray()) as T
        }
        throw IllegalStateException("Unable to instantiate decorators")
    }
}