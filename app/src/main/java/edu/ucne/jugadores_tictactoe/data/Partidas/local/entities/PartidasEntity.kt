package edu.ucne.jugadores_tictactoe.data.Partidas.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "Partidas")
data class PartidasEntity(
    @PrimaryKey(autoGenerate = true)
    val partidaId: Int? = null,
    val fecha: LocalDate = LocalDate.now(),
    val jugador1Id: Int? = null,
    val jugador2Id: Int? = null,
    val ganadorId: Int? = null,
    val esFinalizada: String = ""

    )
