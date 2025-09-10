package edu.ucne.jugadores_tictactoe.data.jugadores.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import edu.ucne.jugadores_tictactoe.data.jugadores.local.database.JugadorDb
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext applicationContext: Context): JugadorDb {
        return Room.databaseBuilder(
            applicationContext,
            JugadorDb::class.java,
            "JugadorDb"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideJugadoresDao(appDataDb: JugadorDb) = appDataDb.jugadorDao()


}