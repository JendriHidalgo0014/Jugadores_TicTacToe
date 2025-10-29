package edu.ucne.jugadores_tictactoe.domain.Jugadorusecase

import edu.ucne.jugadores_tictactoe.domain.model.Jugadores
import edu.ucne.jugadores_tictactoe.domain.repository.JugadorRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetJugadoresUseCase @Inject constructor(
    private val repository: JugadorRepository
) {
    operator fun invoke(): Flow<List<Jugadores>> {
        return repository.getAllFlow()
    }
}