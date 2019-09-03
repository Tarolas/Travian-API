package org.github.tarolas.travian.application

import javafx.application.Application
import javafx.application.Platform
import javafx.scene.Scene
import javafx.stage.Stage
import org.github.tarolas.travian.application.TravianAppConstants.CSS_PATH
import org.github.tarolas.travian.application.TravianAppConstants.HEIGHT
import org.github.tarolas.travian.application.TravianAppConstants.PROJECT_TITLE
import org.github.tarolas.travian.application.TravianAppConstants.WIDTH
import org.github.tarolas.travian.engine.EngineConfiguration
import org.github.tarolas.travian.engine.TravianEngineBuilder
import org.github.tarolas.travian.engine.entities.LoginParams
import org.github.tarolas.travian.service.ServiceConfiguration
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.runApplication
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.context.annotation.Import

@SpringBootApplication
@Import(EngineConfiguration::class, ServiceConfiguration::class)
class TravianApplication : Application() {

    private val log = LoggerFactory.getLogger(TravianApplication::class.java)

    private lateinit var springContext: ConfigurableApplicationContext
    private lateinit var scene: Scene

    override fun init() {
        super.init()
        springContext = runApplication<TravianApplication>()
        //val engine  = TravianEngineBuilder.getEngine(springContext, LoginParams("tdx96", "pocrl", "tx3.travian.pt"))
        val controller = springContext.getBean(TravianController::class.java)
        scene = Scene(controller.view)
        scene.stylesheets.add(CSS_PATH)
    }

    override fun start(stage: Stage?) {
        startApplication(stage!!)
    }

    private fun startApplication(primaryStage: Stage) {
        log.info("Starting {}!", PROJECT_TITLE)
        primaryStage.title = PROJECT_TITLE
        primaryStage.height = HEIGHT
        primaryStage.width = WIDTH
        primaryStage.centerOnScreen()
        primaryStage.setOnCloseRequest { e ->
            Platform.exit()
            System.exit(0)
        }
        primaryStage.scene = scene
        primaryStage.show()
    }

}


fun main(args: Array<String>) {
    Application.launch(TravianApplication::class.java, *args)
}