package org.github.tarolas.travian.engine

import org.github.tarolas.travian.engine.operation.Operation
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient

class OperationFactoryImpl(
        val client: WebClient
) : OperationFactory, ApplicationContextAware {

    private lateinit var context: ApplicationContext

    override fun setApplicationContext(applicationContext: ApplicationContext) {
        context = applicationContext
    }

    override fun <R, P> get(clazz: Class<out Operation<R, P>>): Operation<R, P> {
        for (ctor in clazz.constructors) {
            val parametersTypes = ctor.parameterTypes

            val objs = parametersTypes.map {
                when {
                    it.isAssignableFrom(WebClient::class.java) -> client
                    else -> context.getBean(it)
                }
            }

            return ctor.newInstance(*objs.toTypedArray()) as Operation<R, P>
        }
        throw IllegalStateException("Unable to instantiate decorators")
    }
}