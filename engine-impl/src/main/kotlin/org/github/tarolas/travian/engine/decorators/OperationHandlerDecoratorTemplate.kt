package org.github.tarolas.travian.engine.decorators

import org.github.tarolas.travian.engine.operation.Operation
import org.github.tarolas.travian.engine.OperationHandler
import org.slf4j.LoggerFactory

abstract class OperationHandlerDecoratorTemplate(private val decoratedOperationHandler: OperationHandler) : OperationHandler {

    private val LOG = LoggerFactory.getLogger(OperationHandlerDecoratorTemplate::class.java)

    open fun <R, P> doBeforeExecute(operation: Operation<R, P>, params: P) {}

    open fun <R, P> doAfterExecute(operation: Operation<R, P>, params: P, result: R?) {}

    open fun <R, P> doAfterExecuteWithException(operation: Operation<R, P>, params: P, result: R?, e: Exception) {}

    override suspend fun <R, P> execute(operation: Operation<R, P>, params: P): R? {
        var result: R? = null

        doBeforeExecute(operation, params)
        try {
            result = decoratedOperationHandler.execute(operation, params)

            doAfterExecute(operation, params, result)
        } catch (e: Exception) {
            doAfterExecuteWithException(operation, params, result, e)
            throw e
        }

        return result
    }
}