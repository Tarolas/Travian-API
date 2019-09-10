package org.github.tarolas.travian.application.utils

import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware

/**
 * @author tiago.ribeiro
 */
abstract class SpringContext() : ApplicationContextAware {

    lateinit var context: ApplicationContext

    final override fun setApplicationContext(applicationContext: ApplicationContext) {
        context = applicationContext
    }

    inline fun <reified T> getBean() = context.getBean(T::class.java)
}