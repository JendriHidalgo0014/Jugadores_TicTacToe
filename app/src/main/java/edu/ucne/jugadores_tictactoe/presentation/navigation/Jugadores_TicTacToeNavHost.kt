package edu.ucne.jugadores_tictactoe.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import edu.ucne.jugadores_tictactoe.presentation.Jugadortareas.edit.EditJugadorScreen
import edu.ucne.jugadores_tictactoe.presentation.Jugadortareas.list.ListJugadorScreen
import edu.ucne.jugadores_tictactoe.presentation.Logrotareas.edit.LogrosScreen
import edu.ucne.jugadores_tictactoe.presentation.Partidatareas.edit.EditPartidaScreen
import edu.ucne.jugadores_tictactoe.presentation.Partidatareas.list.ListPartidaScreen
import edu.ucne.jugadores_tictactoe.presentation.TicTacToe.TicTacToeScreen

@Composable
fun Jugadores_TicTacToeNavHost(
    navHostController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navHostController,
        startDestination = "jugador_list",
        modifier = modifier
    ) {
        composable("jugador_list") {
            ListJugadorScreen(
                onNavigateToCreate = { navHostController.navigate("edit_jugador/null") },
                onNavigateToEdit = { id -> navHostController.navigate("edit_jugador/$id") },
                onNavigateToTicTacToe = { navHostController.navigate("tic_tac_toe") },
                onNavigateToPartidas = { navHostController.navigate("partida_list") },
                onNavigateToLogros = { jugadorId ->
                    navHostController.navigate("logros_jugador/$jugadorId")
                }
            )
        }

        composable(
            route = "edit_jugador/{jugadorId}",
            arguments = listOf(navArgument("jugadorId") {
                type = NavType.StringType
                nullable = true
            })
        ) { backStackEntry ->
            val jugadorId = backStackEntry.arguments?.getString("jugadorId")
                ?.takeIf { it != "null" }?.toIntOrNull()
            EditJugadorScreen(
                jugadorId = jugadorId,
                onSaveSuccess = { navHostController.popBackStack() }
            )
        }

        composable("partida_list") {
            ListPartidaScreen(
                onNavigateToDetail = { partidaId ->
                    navHostController.navigate("edit_partida/$partidaId")
                },
                onNavigateBack = { navHostController.popBackStack() }
            )
        }

        composable(
            route = "edit_partida/{partidaId}",
            arguments = listOf(navArgument("partidaId") {
                type = NavType.StringType
                nullable = true
            })
        ) { backStackEntry ->
            val partidaId = backStackEntry.arguments?.getString("partidaId")
                ?.takeIf { it != "null" }?.toIntOrNull()
            EditPartidaScreen(
                partidaId = partidaId,
                onSaveSuccess = { navHostController.popBackStack() }
            )
        }

        composable(
            route = "logros_jugador/{jugadorId}",
            arguments = listOf(navArgument("jugadorId") { type = NavType.IntType })
        ) { backStackEntry ->
            val jugadorId = backStackEntry.arguments?.getInt("jugadorId") ?: 0
            LogrosScreen(
                jugadorId = jugadorId
            )
        }

        composable("tic_tac_toe") {
            TicTacToeScreen()
        }
    }
}