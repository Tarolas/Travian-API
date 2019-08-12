package org.github.tarolas.travian.service.dto

@NoArg
class PlayerDto(
        var id: Long? = null,
        var username: String,
        var server: String,
        var password: String,
        var cookies: MutableSet<CookieDto>
)