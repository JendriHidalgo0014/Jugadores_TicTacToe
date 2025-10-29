package edu.ucne.jugadores_tictactoe.data.local.database

import androidx.room.TypeConverter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class Converters {
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val listAdapter = moshi.adapter<List<Int?>>(
        Types.newParameterizedType(List::class.java, Int::class.javaObjectType)
    )

    @TypeConverter
    fun fromBoardList(value: List<Int?>?): String? {
        return value?.let { listAdapter.toJson(it) }
    }

    @TypeConverter
    fun toBoardList(value: String?): List<Int?>? {
        return value?.let { listAdapter.fromJson(it) }
    }
}