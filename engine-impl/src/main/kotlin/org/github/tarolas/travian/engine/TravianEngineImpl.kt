package org.github.tarolas.travian.engine

import org.github.tarolas.travian.engine.entities.*
import org.github.tarolas.travian.engine.operation.LoginOperation
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Scope
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import reactor.netty.http.client.HttpClient
import reactor.netty.tcp.ProxyProvider


@Component
@Scope("prototype")
class TravianEngineImpl(operationFactory: OperationFactory, operationExecutorFactory: OperationHandlerDecoratorFactory)
    : EngineTemplate("TRAVIAN ENGINE", operationFactory, operationExecutorFactory), TravianEngineInterface {

    lateinit var username: String
    lateinit var password: String
    lateinit var server: String

    override lateinit var client: WebClient

    override suspend fun login(params: LoginParams): String? {
        username = params.username
        password = params.password
        server = params.server



        client = with(WebClient.builder()) {
            if (params.proxy != null) {
                val httpClient = HttpClient.create()
                        .tcpConfiguration { tcpClient ->
                            tcpClient.proxy { proxy ->
                                proxy.type(ProxyProvider.Proxy.HTTP).host(params.proxy!!.host).port(params.proxy!!.port)
                            }
                        }
                val connector = ReactorClientHttpConnector(httpClient)
                clientConnector(connector)
            }

            baseUrl("https://$server/")
            defaultHeaders {
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
            build()
        }
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