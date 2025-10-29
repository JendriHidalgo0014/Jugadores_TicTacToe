package edu.ucne.jugadores_tictactoe.data.local.remote.DataSource

data class JugadorResponse(
    val jugadorId : Int? = null,
    val nombres: String,
    val email: String,
)