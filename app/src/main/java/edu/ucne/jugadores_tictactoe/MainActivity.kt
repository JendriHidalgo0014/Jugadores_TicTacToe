package edu.ucne.jugadores_tictactoe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import edu.ucne.jugadores_tictactoe.data.presentation.JugadorListScreen
import edu.ucne.jugadores_tictactoe.data.presentation.JugadoresScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = "jugadores_list"
            ) {
                composable("jugadores_list") {
                    JugadorListScreen(
                        scope = rememberCoroutineScope(),
                        onCreate = { navController.navigate("jugadores_create") },
                        onEdit = { jugadorId ->
                            navController.navigate("jugadores_edit/$jugadorId")
                        },
                        onDelete = { jugadorId ->
                            navController.navigate("jugadores_delete/$jugadorId")
                        }

                    )
                }

                composable("jugadores_create") {
                    JugadoresScreen(
                        goBack = { navController.popBackStack() }
                    )
                }

                composable("jugadores_edit/{jugadorId}") { backStackEntry ->
                    val jugadorId = backStackEntry.arguments?.getString("jugadorId")?.toInt() ?: 0
                    JugadorEditScreen(
                        jugadorId = jugadorId,
                        goBack = { navController.popBackStack() }
                    )
                }

                composable("jugadores_delete/{jugadorId}") { backStackEntry ->
                    val jugadorId = backStackEntry.arguments?.getString("jugadorId")?.toInt() ?: 0
                    JugadorDeleteScreen(
                        jugadorId = jugadorId,
                        goBack = { navController.popBackStack() }
                    )
                }

            }
        }
    }
}


