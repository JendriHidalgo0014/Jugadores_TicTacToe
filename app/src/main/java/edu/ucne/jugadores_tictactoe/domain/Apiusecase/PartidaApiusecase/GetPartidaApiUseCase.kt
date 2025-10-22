package edu.ucne.jugadores_tictactoe.domain.Apiusecase.PartidaApiusecase

import edu.ucne.jugadores_tictactoe.domain.model.PartidaApi
import edu.ucne.jugadores_tictactoe.domain.repository.PartidaApiRepository
import javax.inject.Inject

class GetPartidaApiUseCase @Inject constructor(
    private val repository: PartidaApiRepository
) {
    suspend operator fun invoke(): List<PartidaApi> {
        return repository.getPartidas()
    }
}