package edu.ucne.jugadores_tictactoe.domain.Apiusecase.PartidaApiusecase

import edu.ucne.jugadores_tictactoe.domain.model.PartidaApi
import edu.ucne.jugadores_tictactoe.domain.repository.PartidaApiRepository
import javax.inject.Inject

class PostPartidaApiUseCase @Inject constructor(
    private val repository: PartidaApiRepository
) {
    suspend operator fun invoke(player1Id: Int): PartidaApi {
        return repository.createPartida(player1Id)
    }
}