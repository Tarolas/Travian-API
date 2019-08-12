package org.github.tarolas.travian.engine

import org.github.tarolas.travian.engine.operation.Operation

interface OperationFactory {
    fun <R, P> get(clazz: Class<out Operation<R, P>>): Operation<R, P>
}