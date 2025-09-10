package edu.ucne.jugadores_tictactoe.data.jugadores.repository

import edu.ucne.jugadores_tictactoe.data.jugadores.local.dao.JugadorDao
import edu.ucne.jugadores_tictactoe.data.jugadores.local.entities.JugadoresEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class JugadoresRepository @Inject constructor(
    val jugadoresDao: JugadorDao

){
    suspend fun saveJugador(jugador: JugadoresEntity) = jugadoresDao.save(jugador)

    suspend fun findJugador(Id:Int): JugadoresEntity = jugadoresDao.find(Id)

    suspend fun deleteJugador(jugador: JugadoresEntity) =  jugadoresDao.delete(jugador)

    fun getAll(): Flow<List<JugadoresEntity>> = jugadoresDao.getAll()

}