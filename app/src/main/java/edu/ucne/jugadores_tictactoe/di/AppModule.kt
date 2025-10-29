package edu.ucne.jugadores_tictactoe.data.jugadores.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import edu.ucne.jugadores_tictactoe.data.local.dao.LogroDao
import edu.ucne.jugadores_tictactoe.data.local.dao.PartidaDao
import edu.ucne.jugadores_tictactoe.data.local.database.JugadorDatabase
import edu.ucne.jugadores_tictactoe.data.local.remote.ApiService.JugadorApiService
import edu.ucne.jugadores_tictactoe.data.local.remote.ApiService.MovimientoApi
import edu.ucne.jugadores_tictactoe.data.local.remote.DataSource.JugadorRemoteDataSource
import edu.ucne.jugadores_tictactoe.data.repository.JugadoresApiRepositoryImpl
import edu.ucne.jugadores_tictactoe.data.repository.LogrosRepositoryImpl
import edu.ucne.jugadores_tictactoe.data.repository.MovimientoRepositoryImpl
import edu.ucne.jugadores_tictactoe.data.repository.PartidasRepositoryImpl
import edu.ucne.jugadores_tictactoe.domain.repository.JugadorRepository
import edu.ucne.jugadores_tictactoe.domain.repository.MovimientoRepository
import edu.ucne.jugadores_tictactoe.domain.repository.PartidaRepository
import edu.ucne.jugadorestictactoe.data.local.Dao.JugadorDao
import edu.ucne.jugadorestictactoe.domain.repository.LogroRepository
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun provideJugadorDb(@ApplicationContext appContext: Context): JugadorDatabase {
        return Room.databaseBuilder(
            appContext,
            JugadorDatabase::class.java,
            "Jugadores.db"
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideJugadorDao(jugadorDatabase: JugadorDatabase): JugadorDao =
        jugadorDatabase.jugadorDao()

    @Provides
    fun provideLogroDao(jugadorDatabase: JugadorDatabase): LogroDao =
        jugadorDatabase.logroDao()

    @Provides
    fun providePartidaDao(jugadorDatabase: JugadorDatabase): PartidaDao =
        jugadorDatabase.partidaDao()

    @Provides
    @Singleton
    fun provideJugadorRemoteDataSource(
        jugadorApi: JugadorApiService
    ): JugadorRemoteDataSource = JugadorRemoteDataSource(jugadorApi)

    @Provides
    @Singleton
    fun provideJugadorRepository(
        localDataSource: JugadorDao,
        remoteDataSource: JugadorRemoteDataSource
    ): JugadorRepository {
        return JugadoresApiRepositoryImpl(localDataSource, remoteDataSource)
    }

    @Provides
    @Singleton
    fun provideLogroRepository(
        logroDao: LogroDao
    ): LogroRepository = LogrosRepositoryImpl(logroDao)

    @Provides
    @Singleton
    fun providePartidaRepository(
        partidaDao: PartidaDao
    ): PartidaRepository = PartidasRepositoryImpl(partidaDao)

    @Provides
    @Singleton
    fun provideMovimientoRepository(
        movimientoApi: MovimientoApi
    ): MovimientoRepository = MovimientoRepositoryImpl(movimientoApi)
}