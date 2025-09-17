package edu.ucne.jugadores_tictactoe.data.presentation.Partidas

import edu.ucne.jugadores_tictactoe.data.Partidas.local.entities.PartidasEntity
import java.time.LocalDate

data class PartidasUiState(
    val partidaId: Int? = null,
    val fecha: LocalDate = LocalDate.now(),
    val jugador1Id: Int? = null,
    val jugador2Id: Int? = null,
    val ganadorId: Int? = null,
    val esFinalizada: String = "",
    val errorMessage:String? = null,
    val successMessage:String? = null,
    val partidas:List<PartidasEntity> = emptyList()
)
