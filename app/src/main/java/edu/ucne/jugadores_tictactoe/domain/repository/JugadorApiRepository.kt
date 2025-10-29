package edu.ucne.jugadores_tictactoe.domain.repository

import edu.ucne.jugadores_tictactoe.domain.model.Jugadores
import edu.ucne.jugadores_tictactoe.data.local.remote.Resource

interface JugadorApiRepository {
    suspend fun find(id: String): Jugadores?
    suspend fun delete(id: String) : Resource<Unit>
    suspend fun upsert(jugador: Jugadores): Resource<Unit>

}