package org.github.tarolas.travian.engine

import org.github.tarolas.travian.engine.operation.Operation

interface OperationHandler {
    suspend fun <R, P> execute(operation: Operation<R, P>, params: P): R?
}