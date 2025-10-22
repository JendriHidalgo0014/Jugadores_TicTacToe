package edu.ucne.jugadores_tictactoe.data.repository

import edu.ucne.jugadores_tictactoe.data.local.mapper.toDomain
import edu.ucne.jugadores_tictactoe.data.local.mapper.toDto
import edu.ucne.jugadores_tictactoe.data.local.remote.PartidaApi.MovimientoApi
import edu.ucne.jugadores_tictactoe.domain.model.Movimiento
import edu.ucne.jugadores_tictactoe.domain.repository.MovimientoRepository
import javax.inject.Inject

class MovimientoRepositoryImpl @Inject constructor(
    private val api: MovimientoApi
) : MovimientoRepository {

    override suspend fun getMovimientos(partidaId: Int): List<Movimiento> {
        return try {
            api.getMovimientos(partidaId).map { it.toDomain() }
        } catch (e: Exception) {
            println("Error al obtener movimientos: ${e.message}")
            emptyList()
        }
    }

    override suspend fun postMovimiento(movimiento: Movimiento): Movimiento {

        val movimientoDto = movimiento.toDto()

        return try {
            val postedMovimientoDto = api.postMovimiento(movimientoDto)
            postedMovimientoDto.toDomain()
        } catch (e: Exception) {
            println("Error al registrar movimiento: ${e.message}")
            throw e
        }
    }
}