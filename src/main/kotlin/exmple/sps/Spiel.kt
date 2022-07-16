package exmple.sps

import exmple.sps.Ergebnis.SPIELER_GEWINNT
import exmple.sps.Ergebnis.UNENTSCHIEDEN
import exmple.sps.Ergebnis.COMPUTER_GEWINNT
import exmple.sps.Wahl.PAPIER
import exmple.sps.Wahl.SCHERE
import exmple.sps.Wahl.STEIN
import kotlinx.html.ButtonType
import kotlinx.html.FORM
import kotlinx.html.button
import java.util.Locale

enum class Wahl {
    STEIN, PAPIER, SCHERE
}

enum class Ergebnis {
    SPIELER_GEWINNT, COMPUTER_GEWINNT, UNENTSCHIEDEN
}

fun ermittleErgebnis(spielerWahl: Wahl, computerWahl: Wahl): Ergebnis {
    return when {
        spielerWahl == computerWahl -> UNENTSCHIEDEN
        spielerWahl == PAPIER && computerWahl == STEIN -> SPIELER_GEWINNT
        spielerWahl == STEIN && computerWahl == SCHERE -> SPIELER_GEWINNT
        spielerWahl == SCHERE && computerWahl == PAPIER -> SPIELER_GEWINNT
        else -> COMPUTER_GEWINNT
    }
}

fun String.toWahl() = //this ist String
    when (lowercase(Locale.getDefault())) {
        "stein" -> STEIN
        "papier" -> PAPIER
        "schere" -> SCHERE
        else -> null
    }

fun FORM.wahlButton(wahl: String) {
    button(type = ButtonType.submit, name = "wahl") {
        value = wahl.lowercase(Locale.getDefault())
        +wahl
    }
}