package edu.ucne.jugadores_tictactoe.data.local.mapper

import edu.ucne.jugadores_tictactoe.data.local.remote.dto.MovimientoDto
import edu.ucne.jugadores_tictactoe.domain.model.Movimiento

fun Movimiento.toDto() = MovimientoDto(
    partidaId = partidaId,
    jugador = jugador,
    posicionFila = posicionFila,
    posicionColumna = posicionColumna
)

fun MovimientoDto.toDomain() = Movimiento(
    partidaId = partidaId,
    jugador = jugador,
    posicionFila = posicionFila,
    posicionColumna = posicionColumna
)