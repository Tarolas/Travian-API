package org.github.tarolas.travian.service

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.github.tarolas.travian.common.mapper.Mapper
import org.github.tarolas.travian.common.minus
import org.github.tarolas.travian.service.data.PlayerRepository
import org.github.tarolas.travian.service.dto.CookieDto
import org.github.tarolas.travian.service.dto.PlayerDto
import org.github.tarolas.travian.service.model.Cookie
import org.github.tarolas.travian.service.model.Player
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Component
import java.util.*

@Component
class CookieServiceImpl(val repository: PlayerRepository) : CookieService {

    override suspend fun validSession(username: String, server: String): List<CookieDto>? = withContext(Dispatchers.IO) {
        val cookies = repository.findByUsernameAndServer(username, server)?.cookies
        if (cookies.isNullOrEmpty() || cookies.none { it.creationDate - Date() < 2800000 })
            null
        else
            cookies    //.takeIf { it || it.all { it.creationDate - Date() < 2800000 } }
    }.let { Mapper.mapCollection(it, CookieDto::class.java) }

    override suspend fun save(username: String, password: String, server: String, cookies: MutableSet<CookieDto>) = withContext(Dispatchers.IO) {
        val player = repository.findByUsernameAndServer(username, server)
                ?: Player(username = username, server = server, password = password)

        player.cookies = cookies.map { ModelMapper().map(it, Cookie::class.java) }.toMutableSet()

        repository.save(player)
    }.let { Mapper.map(it, PlayerDto::class.java) }

}


