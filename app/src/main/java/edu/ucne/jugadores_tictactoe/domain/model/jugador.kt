package edu.ucne.jugadores_tictactoe.domain.model

import java.util.UUID

data class Jugadores(
    val jugadorId: String = UUID.randomUUID().toString(),
    val remoteId: Int? = null,
    val nombres: String,
    val email: String,
    val isPendingCreate: Boolean = false
)