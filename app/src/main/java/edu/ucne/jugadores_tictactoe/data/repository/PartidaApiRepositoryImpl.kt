package edu.ucne.jugadores_tictactoe.data.repository

import edu.ucne.jugadores_tictactoe.data.local.mapper.toDomain
import edu.ucne.jugadores_tictactoe.data.local.remote.PartidaApi.PartidaApi as PartidaApiService
import edu.ucne.jugadores_tictactoe.data.local.remote.dto.PartidaDto
import edu.ucne.jugadores_tictactoe.domain.repository.PartidaApiRepository
import javax.inject.Inject

class PartidaApiRepositoryImpl @Inject constructor(
    private val api: PartidaApiService
) : PartidaApiRepository {

    override suspend fun getPartidas(): List<edu.ucne.jugadores_tictactoe.domain.model.PartidaApi> {
        return api.getPartidas().map { it.toDomain() }
    }

    override suspend fun getPartida(partidaId: Int): edu.ucne.jugadores_tictactoe.domain.model.PartidaApi {
        return api.getPartida(partidaId).toDomain()
    }

    override suspend fun createPartida(player1Id: Int): edu.ucne.jugadores_tictactoe.domain.model.PartidaApi {
        val newPartidaDto = PartidaDto(
            PartidaId = 0,
            Jugador1Id = player1Id,
            Jugador2Id = null
        )
        return api.createPartida(newPartidaDto).toDomain()
    }

}