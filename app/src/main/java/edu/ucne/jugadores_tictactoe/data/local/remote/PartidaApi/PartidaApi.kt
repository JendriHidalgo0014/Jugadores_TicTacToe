package edu.ucne.jugadores_tictactoe.data.local.remote.PartidaApi

import edu.ucne.jugadores_tictactoe.data.local.remote.dto.PartidaDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PartidaApi {

    @GET("api/Partidas")
    suspend fun getPartidas(): List<PartidaDto>

    @GET("api/Partidas/{id}")
    suspend fun getPartida(@Path("id") id: Int): PartidaDto

    @POST("api/Partidas")
    suspend fun createPartida(@Body partida: PartidaDto): PartidaDto
}