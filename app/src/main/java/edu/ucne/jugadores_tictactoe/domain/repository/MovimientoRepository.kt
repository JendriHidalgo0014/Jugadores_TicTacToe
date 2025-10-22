package edu.ucne.jugadores_tictactoe.domain.repository

import edu.ucne.jugadores_tictactoe.domain.model.Movimiento

interface MovimientoRepository {

    suspend fun getMovimientos(partidaId: Int): List<Movimiento>

    suspend fun postMovimiento(movimiento: Movimiento): Movimiento
}