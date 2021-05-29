package edu.uw.sunny121.dotify




import android.content.Context
import android.util.Log
import androidx.work.*
import java.util.concurrent.TimeUnit

private const val INCREASE_PLAYTIME_WORK_TAG = "INCREASE_PLAYTIME_WORK_TAG"

class IncreasePlayTimeManager(context: Context) {

    private val workManager: WorkManager = WorkManager.getInstance(context)

    fun startIncreasePlaytimePeriodically() {
        if (isPlayTimeIncreaseRunning()) {
            return
        }
        val request = PeriodicWorkRequestBuilder<IncreasePlayTimeWorker>(20, TimeUnit.MINUTES)
            .setInitialDelay(5, TimeUnit.SECONDS)
                .setConstraints(
                        Constraints.Builder()
                                .setRequiredNetworkType(NetworkType.CONNECTED)
                                .build()
                )
                .addTag(INCREASE_PLAYTIME_WORK_TAG)
                .build()

        workManager.enqueue(request)

    }

    fun stopPeriodicallyIncreasing() {
        workManager.cancelAllWorkByTag(INCREASE_PLAYTIME_WORK_TAG)
    }

    private fun isPlayTimeIncreaseRunning(): Boolean {
        return workManager.getWorkInfosByTag(INCREASE_PLAYTIME_WORK_TAG).get().any {
            when(it.state) {
                WorkInfo.State.RUNNING,
                WorkInfo.State.ENQUEUED -> true
                else -> false
            }
        }
    }

}