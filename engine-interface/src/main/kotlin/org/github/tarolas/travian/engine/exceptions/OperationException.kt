package org.github.tarolas.travian.api.exceptions

import java.lang.RuntimeException

abstract class OperationException(message: String?, cause: Throwable?) : RuntimeException(message, cause) {
}