package edu.ucne.jugadores_tictactoe.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import edu.ucne.jugadores_tictactoe.data.local.entities.JugadoresEntity
import edu.ucne.jugadores_tictactoe.data.local.entities.LogrosEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LogroDao {
    @Upsert
    suspend fun save(logro: LogrosEntity)

    @Query("SELECT * FROM Logros WHERE logroId = :id LIMIT 1")
    suspend fun find(id: Int): LogrosEntity

    @Delete
    suspend fun delete(logro: LogrosEntity)

    @Query("SELECT * FROM Logros")
    fun getAll(): Flow<List<LogrosEntity>>
}
