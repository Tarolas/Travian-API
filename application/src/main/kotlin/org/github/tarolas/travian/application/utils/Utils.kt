package org.github.tarolas.travian.application.utils

import org.springframework.beans.factory.BeanFactory
import org.springframework.context.ApplicationContext

/**
 * @author tiago.ribeiro
 */

inline fun <reified T> ApplicationContext.getBean() = this.getBean(T::class.java)