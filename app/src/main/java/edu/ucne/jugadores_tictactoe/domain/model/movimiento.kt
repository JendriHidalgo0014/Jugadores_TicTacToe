package edu.ucne.jugadores_tictactoe.domain.model

data class Movimiento(
    val partidaId: Int? = null,
    val jugador: String,
    val posicionFila: Int,
    val posicionColumna: Int
)