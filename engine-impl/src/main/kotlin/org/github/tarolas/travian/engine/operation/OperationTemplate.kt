package org.github.tarolas.travian.engine.operation

import org.github.tarolas.travian.engine.exceptions.UnsuccessfulOperationException
import org.slf4j.LoggerFactory


abstract class OperationTemplate<R, P>(
        override val operationId: Long
) : Operation<R, P> {

    private val LOG = LoggerFactory.getLogger(OperationTemplate::class.java)

    // Abstract Methods
    protected abstract suspend fun doExecute(params: P, result: R)

    protected abstract fun newResult(): R

    // Hook Methods
    private fun doHandleError(e: Exception, params: P, result: R) {}

    private fun doValidateParameters(params: P) {}

    override fun validateParams(params: P) {
        doValidateParameters(params)
    }

    override suspend fun execute(params: P): R {

        val result = newResult()

        // Execute Operation
        try {

            doExecute(params, result)
            return result
        } catch (e: Exception) {
            LOG.error(String.format("%s - Error on operation. %s", getName(), e.message), e)
            doHandleError(e, params, result)
            throw UnsuccessfulOperationException(e.message, e)
        }
    }

}