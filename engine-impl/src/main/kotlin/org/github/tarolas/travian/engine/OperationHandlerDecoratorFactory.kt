package org.github.tarolas.travian.engine

interface OperationHandlerDecoratorFactory {
    fun <T : OperationHandler> decorate(operationHandler: OperationHandler, decoratorClazz: Class<T>): T
}