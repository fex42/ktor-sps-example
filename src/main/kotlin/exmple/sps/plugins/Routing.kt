package exmple.sps.plugins

import exmple.sps.Wahl
import exmple.sps.ermittleErgebnis
import exmple.sps.toWahl
import exmple.sps.wahlButton
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import io.ktor.http.ContentType
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.html.respondHtml
import io.ktor.server.response.respondRedirect
import io.ktor.server.response.respondText
import kotlinx.html.h1
import kotlinx.html.body
import kotlinx.html.label
import kotlinx.html.form
import kotlinx.html.FormMethod

fun Application.configureRouting() {

    // Starting point for a Ktor app:
    routing {
        get("/") {
            call.respondRedirect("/start")
        }

        get("/spiel") {
            val spielerWahl = call.parameters["wahl"]?.toWahl()
            if (spielerWahl == null) {
                call.respondText("Ungültige Wahl!")
            } else {
                val computerWahl = Wahl.values().random()
                val ergebnis = ermittleErgebnis(spielerWahl, computerWahl)

                call.respondText(
                    """
            <h1>Stein Papier Schere</h1>    
            <p>Spieler hat gewählt $spielerWahl.</p>
            <p>Computer hat gewählt $computerWahl.</p>
            <p>Das Ergebnis ist: $ergebnis.</p>
            """.trimIndent(), ContentType.Text.Html
                )
            }
        }

        get("/start") {
            call.respondHtml {
                body {
                    h1 { text("Stein Papier Schere") }
                    form(action = "/spiel", method = FormMethod.get) {
                        // this ist FORM
                        label { +"Deine Wahl:" }
                        wahlButton("Stein")
                        wahlButton("Papier")
                        wahlButton("Schere")
                    }
                }
            }
        }
    }
}

