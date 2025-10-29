package edu.ucne.jugadores_tictactoe.domain.Jugadorusecase

import edu.ucne.jugadores_tictactoe.data.local.remote.Resource
import edu.ucne.jugadores_tictactoe.domain.repository.JugadorRepository
import javax.inject.Inject

class RefreshJugadoresUseCase @Inject constructor(
    private val repository: JugadorRepository
) {
    suspend operator fun invoke(): Resource<Unit> {
        return repository.refreshJugadores()
    }
}