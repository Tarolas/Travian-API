package org.github.tarolas.travian.engine

import org.github.tarolas.travian.engine.operation.Operation
import org.github.tarolas.travian.engine.exceptions.InvalidParametersException
import org.github.tarolas.travian.engine.exceptions.OperationException
import org.github.tarolas.travian.engine.exceptions.UnsuccessfulOperationException
import org.slf4j.LoggerFactory


class DefaultOperationHandler : OperationHandler {
    private val LOG = LoggerFactory.getLogger(DefaultOperationHandler::class.java)

    override suspend fun <R, P> execute(operation: Operation<R, P>, params: P): R? {

        try {
            operation.validateParams(params)
        } catch (e: InvalidParametersException) {
            LOG.warn("Error in parameter validation.")
            throw e
        }

        try {
            return operation.execute(params)
        } catch (e: OperationException) {
            throw e
        } catch (e: Exception) {
            throw UnsuccessfulOperationException(e.message, e)
        }

    }
}