package edu.ucne.jugadores_tictactoe.domain.Jugadorusecase

import edu.ucne.jugadores_tictactoe.domain.model.Jugadores
import edu.ucne.jugadores_tictactoe.domain.repository.JugadorRepository

class GetJugadorUseCase (
    private val repository: JugadorRepository
) {
    suspend operator fun invoke(id: String): Jugadores? {
        return repository.find(id)
    }
}