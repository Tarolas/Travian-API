package org.github.tarolas.travian.engine

import org.github.tarolas.travian.engine.entities.*
import org.github.tarolas.travian.engine.operation.LoginOperation
import org.springframework.context.ApplicationContext
import org.springframework.http.HttpHeaders
import org.springframework.web.reactive.function.client.WebClient

class TravianEngineImpl(
        val username: String,
        val password: String,
        val server: String,
        client: WebClient = WebClient.builder()
                .baseUrl("https://$server/")
                .defaultHeaders {
                    it.set(HttpHeaders.ACCEPT, "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
                    it.set(HttpHeaders.ACCEPT_ENCODING, "gzip, deflate")
                    it.set(HttpHeaders.ACCEPT_LANGUAGE, "en-US,en;q=0.8")
                    it.set(HttpHeaders.CACHE_CONTROL, "max-age=0")
                    it.set(HttpHeaders.CONNECTION, "keep-alive")
                    it.set(HttpHeaders.HOST, /*"Host:" +*/  server)
                    it.set(HttpHeaders.REFERER, "https://$server/")
                    it.set("Upgrade-Insecure-Requests", "1")
                    it.set(HttpHeaders.USER_AGENT, "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36")
                }
                .build()
) : EngineTemplate("TRAVIAN ENGINE", client), TravianEngineInterface {

    override suspend fun login(params: LoginParams): String? {
        return initOperationHandlerBuilder().execute(LoginOperation::class.java, params)
    }

    override suspend fun getDorf1(params: GetDorf1Params): Dorf1 {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getDorf2(params: GetDorf2Params): Dorf2 {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getProfile(params: GetProfileParams): Profile {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}