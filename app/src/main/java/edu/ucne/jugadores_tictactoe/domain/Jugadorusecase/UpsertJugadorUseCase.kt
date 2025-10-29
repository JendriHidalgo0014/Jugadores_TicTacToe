package edu.ucne.jugadores_tictactoe.domain.usecase

  import edu.ucne.jugadores_tictactoe.domain.model.Jugadores
  import edu.ucne.jugadores_tictactoe.data.local.remote.Resource
  import edu.ucne.jugadores_tictactoe.domain.repository.JugadorRepository
  import javax.inject.Inject

class UpsertJugadorUseCase @Inject constructor(
    private val repo: JugadorRepository
) {
    suspend operator fun invoke(jugador: Jugadores): Resource<Unit> = repo.upsert(jugador)
}
