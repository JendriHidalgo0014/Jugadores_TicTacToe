package edu.ucne.jugadores_tictactoe.data.Partidas.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import edu.ucne.jugadores_tictactoe.data.Partidas.local.entities.JugadoresEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface JugadorDao {

    @Upsert
    suspend fun save(jugador: JugadoresEntity)

    @Query("SELECT * FROM Jugadores WHERE jugadorId = :id LIMIT 1")
    suspend fun find(id: Int): JugadoresEntity

    @Delete
    suspend fun delete(jugador: JugadoresEntity)

    @Query("SELECT * FROM Jugadores")
    fun getAll(): Flow<List<JugadoresEntity>>
}