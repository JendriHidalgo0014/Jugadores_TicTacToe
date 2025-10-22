package edu.ucne.jugadores_tictactoe.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import edu.ucne.jugadores_tictactoe.data.local.remote.PartidaApi.MovimientoApi
import edu.ucne.jugadores_tictactoe.data.local.remote.PartidaApi.PartidaApi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ApiModule {
    const val BASE_URL = "https://gestionhuacalesapi.azurewebsites.net/api/"

    @Provides
    @Singleton
    fun provideMoshi(): Moshi =
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Provides
    @Singleton
    fun providePartidaApi(retrofit: Retrofit): PartidaApi {
        return retrofit.create(PartidaApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMovimientoApi(retrofit: Retrofit): MovimientoApi {
        return retrofit.create(MovimientoApi::class.java)
    }
}