package edu.ucne.jugadores_tictactoe.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import edu.ucne.jugadores_tictactoe.data.local.remote.ApiService.MovimientoApi
import edu.ucne.jugadores_tictactoe.data.local.remote.ApiService.PartidaApi
import edu.ucne.jugadores_tictactoe.data.local.remote.ApiService.JugadorApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ApiModule {
    const val BASE_URL = "https://gestionhuacalesapi.azurewebsites.net/api/Jugadores/"
    const val BASE_URL_2 = "https://gestionhuacalesapi.azurewebsites.net/api/Movimientos/1/"


    @Provides
    @Singleton
    fun provideMoshi(): Moshi =
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

    // ← NUEVO: Logging para debugging
    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    // ← NUEVO: OkHttpClient con logging
    @Provides
    @Singleton
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(moshi: Moshi, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
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

    // ← NUEVO: Proveer JugadorApi
    @Provides
    @Singleton
    fun provideJugadorApi(retrofit: Retrofit): JugadorApiService {
        return retrofit.create(JugadorApiService::class.java)
    }
}