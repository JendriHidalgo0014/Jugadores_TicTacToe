package edu.ucne.jugadores_tictactoe.domain.Apiusecase.Movimientousecase

import edu.ucne.jugadores_tictactoe.domain.model.Movimiento
import edu.ucne.jugadores_tictactoe.domain.repository.MovimientoRepository
import javax.inject.Inject

class GetMovimientoUseCase @Inject constructor(
    private val repository: MovimientoRepository
) {
    suspend operator fun invoke(partidaId: Int): List<Movimiento> {
        return repository.getMovimientos(partidaId)
    }
}