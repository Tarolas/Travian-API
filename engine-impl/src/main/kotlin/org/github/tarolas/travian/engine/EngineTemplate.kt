package org.github.tarolas.travian.engine

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.web.reactive.function.client.WebClient

abstract class EngineTemplate(
        val name: String,
        private val operationFactory: OperationFactory,
        private val operationExecutorFactory: OperationHandlerDecoratorFactory
) {

    abstract var client: WebClient

    private val LOG = LoggerFactory.getLogger(EngineTemplate::class.java)

    fun initOperationHandlerBuilder(): OperationHandlerBuilder {
        return OperationHandlerBuilder.initOperationBuilder(client, operationFactory, operationExecutorFactory)
    }
}