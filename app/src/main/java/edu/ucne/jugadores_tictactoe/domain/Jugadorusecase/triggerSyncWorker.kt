package edu.ucne.jugadores_tictactoe.domain.Jugadorusecase

import android.content.Context
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import edu.ucne.jugadores_tictactoe.data.local.remote.SyncWorker

fun triggerSyncWorker(context: Context) {
    val request = OneTimeWorkRequestBuilder<SyncWorker>().build()
    WorkManager.getInstance(context).enqueue(request)
}