package org.github.tarolas.travian.engine

import org.springframework.web.reactive.function.client.WebClient

interface OperationHandlerDecoratorFactory {
    fun <T : OperationHandler> decorate(operationHandler: OperationHandler, client: WebClient, decoratorClazz: Class<T>): T
}