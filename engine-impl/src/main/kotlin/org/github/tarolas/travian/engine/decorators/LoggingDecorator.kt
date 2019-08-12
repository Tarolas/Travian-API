package org.github.tarolas.travian.engine.decorators

import org.github.tarolas.travian.engine.operation.Operation
import org.github.tarolas.travian.engine.OperationHandler
import org.slf4j.LoggerFactory

class LoggingDecorator(decoratedOperationHandler: OperationHandler) : OperationHandlerDecoratorTemplate(decoratedOperationHandler) {

    private val LOG = LoggerFactory.getLogger(LoggingDecorator::class.java)

    override fun <R, P> doBeforeExecute(operation: Operation<R, P>, params: P) {
        LOG.debug("[" + operation.getName() + "] - " + operation.getName() + " Begin");
    }

    override fun <R, P> doAfterExecute(operation: Operation<R, P>, params: P, result: R?) {
        LOG.debug("[" + operation.getName() + "] - " + operation.getName() + " End");
    }
}
