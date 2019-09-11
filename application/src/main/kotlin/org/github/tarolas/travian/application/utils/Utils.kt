package org.github.tarolas.travian.application.utils

import kotlinx.coroutines.*
import org.springframework.beans.factory.BeanFactory
import org.springframework.context.ApplicationContext
import reactor.core.publisher.toMono
import tornadofx.*

/**
 * @author tiago.ribeiro
 */

inline fun <reified T> ApplicationContext.getBean() = this.getBean(T::class.java)

fun <T> Component.runAsyncSuspend(status: TaskStatus? = find(scope), func: suspend FXTask<*>.() -> T) = runAsync(status) {
    runBlocking(Dispatchers.Main) {
        this@runAsync.func()
    }
}


//fun <T> runAsync(status: TaskStatus? = find(scope), func: FXTask<*>.() -> T) = task(status, func)