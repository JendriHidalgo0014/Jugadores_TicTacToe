package edu.ucne.jugadores_tictactoe.data.Partidas.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import edu.ucne.jugadores_tictactoe.data.Partidas.local.dao.JugadorDao
import edu.ucne.jugadores_tictactoe.data.Partidas.local.dao.PartidaDao
import edu.ucne.jugadores_tictactoe.data.Partidas.local.entities.JugadoresEntity
import edu.ucne.jugadores_tictactoe.data.Partidas.local.entities.PartidasEntity

@Database(
    entities = [PartidasEntity::class,
               JugadoresEntity::class],
    version = 1,
    exportSchema = false
)

abstract class PartidaDb : RoomDatabase() {

    abstract fun partidaDao(): PartidaDao

    abstract fun jugadorDao(): JugadorDao

}