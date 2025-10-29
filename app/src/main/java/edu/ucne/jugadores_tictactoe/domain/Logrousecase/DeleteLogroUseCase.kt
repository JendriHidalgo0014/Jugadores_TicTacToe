package edu.ucne.jugadores_tictactoe.domain.Logrousecase

import edu.ucne.jugadores_tictactoe.domain.model.Logro
import edu.ucne.jugadorestictactoe.domain.repository.LogroRepository
import javax.inject.Inject

class DeleteLogroUseCase @Inject constructor(
    private val repository: LogroRepository
) {
    suspend operator fun invoke(logro: Logro) {
        repository.delete(logro)
    }
}