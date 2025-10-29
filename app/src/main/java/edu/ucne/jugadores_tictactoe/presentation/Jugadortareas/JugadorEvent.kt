package edu.ucne.jugadores_tictactoe.presentation.Jugadortareas

import edu.ucne.jugadores_tictactoe.domain.model.Jugadores

sealed interface JugadorEvent {
    data class CrearJugador(val nombres: String) : JugadorEvent
    data class UpdateJugador(val jugador: Jugadores) : JugadorEvent
    data class DeleteJugador(val id: String) : JugadorEvent
    object ShowCreateSheet : JugadorEvent
    object HideCreateSheet : JugadorEvent
    data class OnDescriptionChange(val description: String) : JugadorEvent
    object UserMessageShown : JugadorEvent
}