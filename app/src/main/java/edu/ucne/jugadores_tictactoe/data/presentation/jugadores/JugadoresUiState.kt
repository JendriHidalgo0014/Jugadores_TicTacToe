package edu.ucne.jugadores_tictactoe.data.presentation.jugadores

import edu.ucne.jugadores_tictactoe.data.jugadores.local.entities.JugadoresEntity

data class JugadoresUiState (

    val jugadorId: Int? = null,
    val nombre:String = "",
    val partidas:Int = 0,
    val errorMessage:String? = null,
    val successMessage:String? = null,
    val jugadores:List<JugadoresEntity> = emptyList()
)