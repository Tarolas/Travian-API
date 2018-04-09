package org.github.tarolas.tbotapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TbotapiApplication

fun main(args: Array<String>) {
    runApplication<TbotapiApplication>(*args)
}
