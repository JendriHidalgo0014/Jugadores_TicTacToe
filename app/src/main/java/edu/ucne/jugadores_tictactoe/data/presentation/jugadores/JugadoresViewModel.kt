package edu.ucne.jugadores_tictactoe.data.presentation.jugadores

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.jugadores_tictactoe.data.Partidas.local.entities.JugadoresEntity
import edu.ucne.jugadores_tictactoe.data.Partidas.repository.JugadoresRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JugadoresViewModel @Inject constructor(

    private val jugadoresRepository: JugadoresRepository

): ViewModel() {

    private val _uiState = MutableStateFlow(JugadoresUiState())
    val uiState get() = _uiState.asStateFlow()


    init{
        getJugador()

    }

    fun getJugador(){
        viewModelScope.launch {
            jugadoresRepository.jugadoresDao.getAll().collect{jugador->
                _uiState.update {
                    it.copy(jugadores = jugador)
                }
            }
        }
    }


    fun saveJugador(){
        viewModelScope.launch {
            if(_uiState.value.nombre.isBlank() || _uiState.value.partidas == null){
                _uiState.update {
                    it.copy(
                        errorMessage = "Todos los campos deben ser rellenados!", successMessage = null
                    )
                }
                return@launch
            }
            try{
                jugadoresRepository.saveJugador(_uiState.value.toEntity())
                _uiState.update {
                    it.copy(
                        successMessage = "El jugador se ha guardado con exito!!", errorMessage = null
                    )
                }

                nuevoJugador()
            }catch(e:Exception){
                _uiState.update {
                    it.copy(
                        errorMessage = "Hubo un error al guardar el jugador", successMessage = null
                    )
                }
            }
        }

    }

    fun deleteJugador(){
        viewModelScope.launch {
            try{
                jugadoresRepository.deleteJugador(_uiState.value.toEntity())
                _uiState.update {
                    it.copy(
                        successMessage = "El jugador se ha eliminado con exito!!", errorMessage = null
                    )
                }

            }catch(e:Exception){
                _uiState.update {
                    it.copy(
                        errorMessage = "Hubo un error al eliminar el juagdor"
                    )
                }
            }
        }

    }

    fun nuevoJugador(){
        _uiState.update {
            it.copy(
                jugadorId = null,
                nombre = "",
                partidas = 0

            )
        }

    }

    fun selectJugador(jugadorId:Int) {
        viewModelScope.launch {
            val jugador = jugadoresRepository.findJugador(jugadorId)
            if (jugador != null) {
                _uiState.update {
                    it.copy(
                        jugadorId = jugador.jugadorId,
                        nombre = jugador.nombre,
                        partidas = jugador.partidas
                    )
                }
            } else {
                _uiState.update {
                    it.copy(
                        errorMessage = "No se encontró el jugador con ID $jugadorId"
                    )
                }
            }
        }
    }


    fun onChangeNombre(nombre:String){
        _uiState.update {
            it.copy(
                nombre = nombre
            )
        }
    }

    fun onChangePartidas(partidas:Int){
        _uiState.update {
            it.copy(
                partidas = partidas
            )
        }
    }


    fun JugadoresUiState.toEntity() = JugadoresEntity(
        jugadorId = jugadorId,
        nombre = nombre,
        partidas = partidas ?: 0

    )

}