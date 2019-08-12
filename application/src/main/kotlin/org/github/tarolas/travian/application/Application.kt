package org.github.tarolas.travian.application

import org.github.tarolas.travian.engine.EngineConfiguration
import org.github.tarolas.travian.engine.TravianEngineBuilder
import org.github.tarolas.travian.engine.TravianEngineImpl
import org.github.tarolas.travian.engine.entities.LoginParams
import org.github.tarolas.travian.service.ServiceConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import

@SpringBootApplication
@Import(EngineConfiguration::class, ServiceConfiguration::class)
class Application

fun main(args: Array<String>) {
    val application = runApplication<Application>(*args)

    val engine  = TravianEngineBuilder.getEngine(LoginParams("tdx96", "pocrl", "tx3.travian.pt"))

}