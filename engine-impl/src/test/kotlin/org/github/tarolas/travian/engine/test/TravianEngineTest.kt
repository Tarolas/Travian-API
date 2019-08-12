package org.github.tarolas.travian.engine.test

import kotlinx.coroutines.runBlocking
import org.github.tarolas.travian.engine.EngineConfiguration
import org.github.tarolas.travian.engine.TravianEngineImpl
import org.github.tarolas.travian.engine.TravianEngineInterface
import org.github.tarolas.travian.engine.entities.LoginParams
import org.github.tarolas.travian.service.ServiceConfiguration
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Import
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
open class TravianEngineTest {

    @Autowired
    lateinit var engine: TravianEngineInterface


    @Test
    fun test() {
        runBlocking {
            println(engine.login(LoginParams("tdx96", "pocrl", "tx3.travian.pt")))
        }
    }

    @SpringBootApplication
    @Import(EngineConfiguration::class, ServiceConfiguration::class)
    open class TestConfiguration {

        @Bean
        open fun getEngine(context: ApplicationContext
        ): TravianEngineInterface {
            return TravianEngineImpl(
                    "tdx96",
                    "pocrl",
                    "tx3.travian.pt",
                    context
            )
        }
    }

}

