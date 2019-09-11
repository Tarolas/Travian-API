package org.github.tarolas.travian.service.dto

import java.time.Duration
import java.util.*

@NoArg
class CookieDto(
        var id: Long? = null,
        var name: String?,
        var value: String?,
        var httpOnly: Boolean?,
        var secure: Boolean?,
        var path: String?,
        var domain: String?,
        var maxAge: Duration?,
        var creationDate: Date?,
        var updateDate: Date?
)