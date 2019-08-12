package org.github.tarolas.travian.engine

import kotlinx.coroutines.runBlocking
import org.github.tarolas.travian.engine.entities.LoginParams
import org.github.tarolas.travian.engine.exceptions.OperationException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap


object TravianEngineBuilder {

    private val engineCache: ConcurrentMap<Int, TravianEngineInterface> = ConcurrentHashMap(16)

    fun getEngine(loginParams: LoginParams): TravianEngineInterface = engineCache.getOrPut(loginParams.hashCode()) {
        val (username, password, server) = loginParams
        val engine = TravianEngineImpl(username, password, server)
        runBlocking {
            engine.login(loginParams)
        }
        engine
    }
}