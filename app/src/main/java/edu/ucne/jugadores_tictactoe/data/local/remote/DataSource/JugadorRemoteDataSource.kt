package edu.ucne.jugadores_tictactoe.data.local.remote.DataSource

import edu.ucne.jugadores_tictactoe.data.local.remote.ApiService.JugadorApiService
import edu.ucne.jugadores_tictactoe.data.local.remote.Resource
import javax.inject.Inject

class JugadorRemoteDataSource @Inject constructor(
    private val api: JugadorApiService
) {
    suspend fun createJugador(request: JugadorRequest): Resource<JugadorResponse> {
        return try {
            val response = api.createJugador(request)
            if (response.isSuccessful) {
                response.body()?.let { Resource.Success(it) }
                    ?: Resource.Error("Respuesta vacía del servidor")
            } else {
                Resource.Error("HTTP ${response.code()} ${response.message()}")
            }
        } catch (e: Exception) {
            Resource.Error(e.localizedMessage ?: "Error de red")
        }
    }

    suspend fun updateJugador(id: Int, request: JugadorRequest): Resource<Unit> {
        return try {
            val response = api.updateJugador(id, request)
            if (response.isSuccessful) {
                Resource.Success(Unit)
            } else {
                Resource.Error("HTTP ${response.code()} ${response.message()}")
            }
        } catch (e: Exception) {
            Resource.Error(e.localizedMessage ?: "Error de red")
        }
    }

    suspend fun deleteJugador(id: Int): Resource<Unit> {
        return try {
            val response = api.deleteJugador(id)
            if (response.isSuccessful) {
                Resource.Success(Unit)
            } else {
                Resource.Error("HTTP ${response.code()} ${response.message()}")
            }
        } catch (e: Exception) {
            Resource.Error(e.localizedMessage ?: "Error de red")
        }
    }

    suspend fun getJugador(id: Int): Resource<JugadorResponse> {
        return try {
            val response = api.getJugador(id)
            if (response.isSuccessful) {
                response.body()?.let { Resource.Success(it) }  // Extraer el body, no el Response completo
                    ?: Resource.Error("Respuesta vacía al obtener el jugador")
            } else {
                Resource.Error("HTTP ${response.code()} al obtener el jugador: ${response.message()}")
            }
        } catch (e: Exception) {
            Resource.Error(e.localizedMessage ?: "Error de red al obtener el jugador")
        }
    }

    suspend fun getJugadores(): Resource<List<JugadorResponse>> {
        return try {
            val response = api.getJugadores()
            if (response.isSuccessful) {
                response.body()?.let { Resource.Success(it) }
                    ?: Resource.Error("Respuesta vacía al obtener lista de jugadores")
            } else {
                Resource.Error("HTTP ${response.code()} al obtener lista de jugadores: ${response.message()}")
            }
        } catch (e: Exception) {
            Resource.Error(e.localizedMessage ?: "Error de red al obtener jugadores")
        }
    }
}