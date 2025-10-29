package edu.ucne.jugadores_tictactoe.domain.Jugadorusecase

import edu.ucne.jugadores_tictactoe.domain.usecase.DeleteJugadorUseCase

data class JugadorUseCases(
    val createJugadorLocalUseCase: CreateJugadorLocalUseCase,
    val guardarJugador: SaveJugadorUseCase,
    val eliminarJugador: DeleteJugadorUseCase,
    val obtenerJugador: GetJugadorUseCase,
    val obtenerJugadores: GetJugadorUseCase,
)