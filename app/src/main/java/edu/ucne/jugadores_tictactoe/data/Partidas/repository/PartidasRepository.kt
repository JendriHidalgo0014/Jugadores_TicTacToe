package edu.ucne.jugadores_tictactoe.data.Partidas.repository

import edu.ucne.jugadores_tictactoe.data.Partidas.local.dao.PartidaDao
import edu.ucne.jugadores_tictactoe.data.Partidas.local.entities.PartidasEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PartidasRepository @Inject constructor(
    val partidasDao: PartidaDao

){
    suspend fun savePartida(partida: PartidasEntity) = partidasDao.save(partida)

    suspend fun findPartida(Id:Int): PartidasEntity = partidasDao.find(Id)

    suspend fun deletePartida(partida: PartidasEntity) =  partidasDao.delete(partida)

    fun getAll(): Flow<List<PartidasEntity>> = partidasDao.getAll()

}