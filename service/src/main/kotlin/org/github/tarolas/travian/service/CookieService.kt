package org.github.tarolas.travian.service

import org.github.tarolas.travian.service.dto.CookieDto
import org.github.tarolas.travian.service.dto.PlayerDto

interface CookieService {
    suspend fun validSession(username: String, server: String): List<CookieDto>?
    suspend fun save(username: String, password: String, server: String, cookies: MutableSet<CookieDto>): PlayerDto
}