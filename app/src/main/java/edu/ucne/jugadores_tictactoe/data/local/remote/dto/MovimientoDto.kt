package edu.ucne.jugadores_tictactoe.data.local.remote.dto

import com.google.gson.annotations.SerializedName

data class MovimientoDto(
    @SerializedName("partidaId")
    val partidaId: Int? = null,

    @SerializedName("jugador")
    val jugador: String,

    @SerializedName("posicionFila")
    val posicionFila: Int,

    @SerializedName("posicionColumna")
    val posicionColumna: Int
)