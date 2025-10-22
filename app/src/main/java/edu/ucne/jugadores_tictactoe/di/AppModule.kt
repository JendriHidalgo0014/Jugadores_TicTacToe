package edu.ucne.jugadores_tictactoe.data.jugadores.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import edu.ucne.jugadores_tictactoe.data.local.database.JugadorDatabase
import edu.ucne.jugadores_tictactoe.data.repository.JugadoresRepositoryImpl
import edu.ucne.jugadores_tictactoe.data.repository.LogrosRepositoryImpl
import edu.ucne.jugadores_tictactoe.data.repository.MovimientoRepositoryImpl
import edu.ucne.jugadores_tictactoe.data.repository.PartidasRepositoryImpl
import edu.ucne.jugadores_tictactoe.domain.repository.JugadorRepository
import edu.ucne.jugadores_tictactoe.domain.repository.MovimientoRepository
import edu.ucne.jugadores_tictactoe.domain.repository.PartidaRepository
import edu.ucne.jugadorestictactoe.domain.repository.LogroRepository
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun provideJugadorDb(@ApplicationContext appContext: Context) =
        Room.databaseBuilder(
            appContext,
            JugadorDatabase::class.java,
            "Jugadores.db"
        ).fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideJugadorDao(jugadorDatabase: JugadorDatabase) = jugadorDatabase.JugadorDao()

    @Provides
    fun provideLogroDao(logroDatabase: JugadorDatabase) = logroDatabase.LogroDao()

    @Provides
    fun providePartidaDao(partidaDatabase: JugadorDatabase) = partidaDatabase.PartidaDao()

    @Provides
    @Singleton
    fun provideJugadorRepository(
        jugadorDao: edu.ucne.jugadores_tictactoe.data.local.dao.JugadorDao
    ): JugadorRepository = JugadoresRepositoryImpl(jugadorDao)

    @Provides
    @Singleton
    fun provideLogroRepository(
        logroDao: edu.ucne.jugadores_tictactoe.data.local.dao.LogroDao
    ): LogroRepository = LogrosRepositoryImpl(logroDao)

    @Provides
    @Singleton
    fun providePartidaRepository(
        partidaDao: edu.ucne.jugadores_tictactoe.data.local.dao.PartidaDao
    ): PartidaRepository = PartidasRepositoryImpl(partidaDao)

    @Provides
    @Singleton
    fun provideMovimientoRepository(
        movimientoApi: edu.ucne.jugadores_tictactoe.data.local.remote.PartidaApi.MovimientoApi
    ): MovimientoRepository = MovimientoRepositoryImpl(movimientoApi)
}