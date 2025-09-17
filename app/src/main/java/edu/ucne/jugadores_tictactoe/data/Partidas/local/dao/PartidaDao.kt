package edu.ucne.jugadores_tictactoe.data.Partidas.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import edu.ucne.jugadores_tictactoe.data.Partidas.local.entities.PartidasEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PartidaDao{
    @Upsert
    suspend fun save(partida: PartidasEntity)

    @Query("SELECT * FROM Partidas WHERE partidaId = :id LIMIT 1")
    suspend fun find(id: Int): PartidasEntity

    @Delete
    suspend fun delete(partida: PartidasEntity)

    @Query("SELECT * FROM partidas")
    fun getAll(): Flow<List<PartidasEntity>>
}