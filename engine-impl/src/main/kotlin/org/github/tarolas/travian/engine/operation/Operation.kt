package org.github.tarolas.travian.api.engine.operation

interface Operation<R, P> {

    suspend fun execute(params: P) : R
    fun validateParams(params: P)

    val operationId : Long
    fun getName() = this::class.simpleName
}