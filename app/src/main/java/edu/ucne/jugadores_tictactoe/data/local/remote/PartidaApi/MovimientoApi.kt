package edu.ucne.jugadores_tictactoe.data.local.remote.PartidaApi

import edu.ucne.jugadores_tictactoe.data.local.remote.dto.MovimientoDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface MovimientoApi {

    @GET("api/Movimientos/{partidaId}")
    suspend fun getMovimientos(@Path("partidaId") partidaId: Int): List<MovimientoDto>

    @POST("api/Movimientos")
    suspend fun postMovimiento(@Body movimiento: MovimientoDto): MovimientoDto
}