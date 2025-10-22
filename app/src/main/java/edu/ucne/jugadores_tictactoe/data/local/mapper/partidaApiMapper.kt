package edu.ucne.jugadores_tictactoe.data.local.mapper

import edu.ucne.jugadores_tictactoe.data.local.remote.dto.PartidaDto
import edu.ucne.jugadores_tictactoe.domain.model.PartidaApi

fun PartidaDto.toDomain(): PartidaApi = PartidaApi(
    PartidaId = PartidaId,
    Jugador1Id = Jugador1Id,
    Jugador2Id = Jugador2Id
)

fun PartidaApi.toDto(): PartidaDto = PartidaDto(
    PartidaId = PartidaId,
    Jugador1Id = Jugador1Id,
    Jugador2Id = Jugador2Id
)