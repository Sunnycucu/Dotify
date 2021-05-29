package edu.uw.sunny121.dotify

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import edu.uw.sunny121.dotify.databinding.ActivityMainBinding
import edu.uw.sunny121.dotify.model.Userprofile


class IncreasePlayTimeWorker(
    private val context: Context, workerParameters: WorkerParameters
): CoroutineWorker(context, workerParameters) {
    private val application by lazy { context.applicationContext as MusicApplication }
    private val notificationManager by lazy { application.notificationManager }
    private val dataRepository by lazy { application.dataRepository }

    override suspend fun doWork(): Result {
        return try {
            Log.i("sunny", "workingn nonow")
            val up : Userprofile = dataRepository.getUserProfile()
            notificationManager.publishNewSongNotification()
            return Result.success()
        } catch (ex: Exception) {
            Result.failure()
        }

    }


}