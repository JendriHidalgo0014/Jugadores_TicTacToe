package edu.ucne.jugadores_tictactoe.data.Partidas.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import edu.ucne.jugadores_tictactoe.data.Partidas.local.database.PartidaDb
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Provides
    @Singleton
    fun providePartidasDb(@ApplicationContext applicationContext: Context): PartidaDb =
        Room.databaseBuilder(
            applicationContext,
            PartidaDb::class.java,
            "Partida.db"
        ).fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun providePartidaDao(appDataDb: PartidaDb) = appDataDb.partidaDao()

    @Provides
    @Singleton
    fun provideJugadorDao(appDataDb: PartidaDb) = appDataDb.jugadorDao()
}