package edu.ucne.jugadores_tictactoe.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class JugadorDto(
    @Json(name = "jugadorId")
    val jugadorId: Int? = null,

    @Json(name = "nombres")
    val nombres: String,

    @Json(name = "email")
    val email: String = ""
)