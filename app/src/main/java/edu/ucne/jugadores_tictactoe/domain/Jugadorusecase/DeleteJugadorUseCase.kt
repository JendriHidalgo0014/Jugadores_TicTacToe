package edu.ucne.jugadores_tictactoe.domain.usecase

import edu.ucne.jugadores_tictactoe.data.local.remote.Resource
import edu.ucne.jugadores_tictactoe.domain.repository.JugadorRepository
import javax.inject.Inject

class DeleteJugadorUseCase @Inject constructor(
    private val repo: JugadorRepository
) {
    suspend operator fun invoke(id: String): Resource<Unit> = repo.delete(id)
}

