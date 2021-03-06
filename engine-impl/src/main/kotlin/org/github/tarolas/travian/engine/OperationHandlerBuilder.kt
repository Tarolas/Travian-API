package org.github.tarolas.travian.engine

import org.github.tarolas.travian.engine.operation.Operation
import org.github.tarolas.travian.engine.decorators.LoggingDecorator
import org.springframework.context.ApplicationContext
import org.springframework.web.reactive.function.client.WebClient

class OperationHandlerBuilder(
        private val client: WebClient,
        private val operationFactory: OperationFactory,
        private val operationExecutorFactory: OperationHandlerDecoratorFactory
) {
    private var handler: OperationHandler

    init {
        handler = DefaultOperationHandler()
    }

    suspend fun <R, P> execute(operationClazz: Class<out Operation<R, P>>, params: P): R? {
        val operation = operationFactory.get(client, operationClazz)
        return handler.execute(operation, params)
    }

    companion object {
        fun initOperationBuilder(client: WebClient, operationFactory: OperationFactory, operationExecutorFactory: OperationHandlerDecoratorFactory): OperationHandlerBuilder {
            return OperationHandlerBuilder(client, operationFactory, operationExecutorFactory).withDefaultDecorators()
        }
    }

    fun withCustomDecorator(clazz: Class<out OperationHandler>): OperationHandlerBuilder {
        handler = operationExecutorFactory.decorate(handler, client, clazz)
        return this
    }

    //Default decorators
    private fun withDefaultDecorators(): OperationHandlerBuilder {
        return withLogging()
    }

    private fun withLogging(): OperationHandlerBuilder {
        handler = operationExecutorFactory.decorate(handler, client, LoggingDecorator::class.java)
        return this
    }
}