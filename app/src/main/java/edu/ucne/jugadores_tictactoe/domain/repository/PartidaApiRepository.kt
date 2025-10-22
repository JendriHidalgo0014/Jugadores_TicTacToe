package edu.ucne.jugadores_tictactoe.domain.repository

import edu.ucne.jugadores_tictactoe.domain.model.PartidaApi

interface PartidaApiRepository {
    suspend fun getPartidas(): List<PartidaApi>

    suspend fun getPartida(partidaId: Int): PartidaApi

    suspend fun createPartida(player1Id: Int): PartidaApi
}