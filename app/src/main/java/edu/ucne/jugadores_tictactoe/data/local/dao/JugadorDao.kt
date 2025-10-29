
package edu.ucne.jugadorestictactoe.data.local.Dao

import edu.ucne.jugadores_tictactoe.data.local.entities.JugadoresEntity
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface JugadorDao{
    @Upsert
    suspend fun upsert(jugador: JugadoresEntity)

    @Query(
        "SELECT * FROM Jugadores WHERE remoteId =:id Limit 1"
    )
    suspend fun find(id: String): JugadoresEntity?

    @Query("DELETE FROM Jugadores WHERE jugadorId = :id")
    suspend fun delete(id: String)

    @Query("SELECT * FROM Jugadores")
    fun observeJugadores(): Flow<List<JugadoresEntity>>

    @Query("SELECT * FROM Jugadores WHERE isPendingCreate = 1")
    suspend fun getPendingCreateJugadores(): List<JugadoresEntity>
}
