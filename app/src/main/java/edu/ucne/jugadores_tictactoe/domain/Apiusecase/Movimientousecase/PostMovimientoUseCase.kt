package edu.ucne.jugadores_tictactoe.domain.Apiusecase.Movimientousecase

import edu.ucne.jugadores_tictactoe.domain.model.Movimiento
import edu.ucne.jugadores_tictactoe.domain.repository.MovimientoRepository
import javax.inject.Inject

class PostMovimientoUseCase @Inject constructor(
    private val repository: MovimientoRepository
) {
    suspend operator fun invoke(movimiento: Movimiento): Movimiento {
        return repository.postMovimiento(movimiento)
    }
}