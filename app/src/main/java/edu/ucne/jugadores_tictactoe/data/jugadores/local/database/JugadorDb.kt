package edu.ucne.jugadores_tictactoe.data.jugadores.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import edu.ucne.jugadores_tictactoe.data.jugadores.local.dao.JugadorDao
import edu.ucne.jugadores_tictactoe.data.jugadores.local.entities.JugadoresEntity


@Database(
    entities = [JugadoresEntity::class],
    version = 1,
    exportSchema = false
)

abstract class JugadorDb : RoomDatabase() {
    abstract fun jugadorDao(): JugadorDao
}