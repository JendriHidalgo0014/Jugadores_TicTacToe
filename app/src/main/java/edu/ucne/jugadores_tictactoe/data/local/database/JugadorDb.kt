package edu.ucne.jugadores_tictactoe.data.local.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import android.content.Context
import edu.ucne.jugadores_tictactoe.data.local.dao.LogroDao
import edu.ucne.jugadores_tictactoe.data.local.dao.PartidaDao
import edu.ucne.jugadores_tictactoe.data.local.entities.JugadoresEntity
import edu.ucne.jugadores_tictactoe.data.local.entities.LogrosEntity
import edu.ucne.jugadores_tictactoe.data.local.entities.MovimientoEntity
import edu.ucne.jugadores_tictactoe.data.local.entities.PartidasEntity
import edu.ucne.jugadorestictactoe.data.local.Dao.JugadorDao

@Database(
    entities = [
        JugadoresEntity::class,
        LogrosEntity::class,
        PartidasEntity::class,
        MovimientoEntity::class
    ],
    version = 5,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class JugadorDatabase : RoomDatabase() {
    abstract fun jugadorDao(): JugadorDao
    abstract fun logroDao(): LogroDao
    abstract fun partidaDao(): PartidaDao

    companion object {
        @Volatile
        private var Instance: JugadorDatabase? = null

        fun getDatabase(context: Context): JugadorDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    JugadorDatabase::class.java,
                    "jugador_database"
                ).build().also { Instance = it }
            }
        }
    }
}