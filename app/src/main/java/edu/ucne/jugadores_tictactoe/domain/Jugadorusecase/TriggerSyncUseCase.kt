package edu.ucne.jugadores_tictactoe.domain.Jugadorusecase

import android.content.Context
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import dagger.hilt.android.qualifiers.ApplicationContext
import edu.ucne.jugadores_tictactoe.data.local.remote.SyncWorker
import javax.inject.Inject

class TriggerSyncUseCase@Inject constructor(
    @ApplicationContext private val context: Context
) {
    operator fun invoke() {
        val request = OneTimeWorkRequestBuilder<SyncWorker>().build()
        WorkManager.getInstance(context).enqueue(request)
    }
}