package exmple.sps

import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import exmple.sps.plugins.configureRouting

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        configureRouting()
    }.start(wait = true)
}
