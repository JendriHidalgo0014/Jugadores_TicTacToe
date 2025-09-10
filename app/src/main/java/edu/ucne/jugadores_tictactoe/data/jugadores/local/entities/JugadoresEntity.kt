package edu.ucne.jugadores_tictactoe.data.jugadores.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Jugadores")
data class JugadoresEntity(
    @PrimaryKey(autoGenerate = true)
    val jugadorId: Int? = null,
    val nombre: String = "",
    val partidas: Int = 0

)
