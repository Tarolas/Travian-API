package org.github.tarolas.travian.engine.exceptions

import java.lang.RuntimeException

abstract class OperationException(message: String?, cause: Throwable?) : RuntimeException(message, cause) {
}