package org.github.tarolas.travian.engine.operation

import kotlinx.coroutines.reactive.awaitFirst
import org.github.tarolas.travian.common.TravianPaths
import org.github.tarolas.travian.common.mapper.Mapper
import org.github.tarolas.travian.engine.entities.LoginParams
import org.github.tarolas.travian.service.CookieService
import org.github.tarolas.travian.service.dto.CookieDto
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.client.WebClient

class LoginOperation(
        val client: WebClient,
        val cookieService: CookieService
) : OperationTemplate<String, LoginParams>(OperationIds.LOGIN.id) {

    override suspend fun doExecute(params: LoginParams, result: String) {
        val res = cookieService.validSession(params.username, params.server)?.let { cookies ->
            client.get()
                    .uri(TravianPaths.DORF1.path)
                    .cookies { map ->
                        cookies.forEach {
                            if(it.name != null) {
                                map.add(it.name!!, it.value)
                            }
                        }
                    }
                    .exchange()
                    .awaitFirst()
                    .bodyToMono(String::class.java)
                    .awaitFirst()
        }

        if (res != null)
            result + res
        else {
            val response = client.post()
                    .uri(TravianPaths.DORF1.path)
                    .body(BodyInserters
                            .fromFormData("name", params.username)
                            .with("password", params.password)
                            .with("lowres", "1")
                            .with("s1", "%D0%92%D1%85%D0%BE%D0%B4")
                            .with("w", "1366%3A768")
                            .with("login", "" + System.currentTimeMillis())
                    )
                    .exchange()
                    .awaitFirst()

            val cookies = response.cookies()
                    .toSingleValueMap()
                    .asSequence()
                    .map {
                        Mapper.map(it, CookieDto::class.java)
                    }.toMutableSet()

            cookieService.save(params.username, params.password, params.server, cookies)

            result + response
                    .bodyToMono(String::class.java)
                    .awaitFirst()
        }
    }

    override fun newResult() = ""
}

