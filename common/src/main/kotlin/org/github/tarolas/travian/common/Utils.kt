package org.github.tarolas.travian.common

import java.util.*

operator fun Date.minus(date: Date) = this.time - date.time
