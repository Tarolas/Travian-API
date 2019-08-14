package org.github.tarolas.travian.engine

import org.github.tarolas.travian.engine.operation.Operation
import org.springframework.web.reactive.function.client.WebClient

interface OperationFactory {
    fun <R, P> get(client: WebClient, clazz: Class<out Operation<R, P>>): Operation<R, P>
}