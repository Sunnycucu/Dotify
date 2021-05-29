package edu.uw.sunny121.dotify

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.ericchee.songdataprovider.Song
import com.ericchee.songdataprovider.SongDataProvider
import kotlin.random.Random

import kotlin.random.Random.Default.nextInt


private const val NEW_SONGS_ID = "NEW_SONGS_ID"


class songNotificationManager(
    private val context: Context
) {
    private val notificationManager = NotificationManagerCompat.from(context)

    init {
        // Initialize all channels
        initNotificationChannels()
    }

    fun publishNewSongNotification() {
        val randomSong : Song = SongDataProvider.getAllSongs().random()
        val intent = Intent(context, SongListActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)


        val builder = NotificationCompat.Builder(context, NEW_SONGS_ID)    // channel id from creating the channel
            .setSmallIcon(R.drawable.ic_baseline_queue_music_24)
            .setContentTitle("${randomSong.artist} just released a new song!!")
            .setContentText("Listen to ${randomSong.title} now on Dotify")
            .setContentIntent(pendingIntent)    // sets the action when user clicks on notification
            .setAutoCancel(true)    // This will dismiss the notification tap
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(notificationManager) {
            val notificationId = Random.nextInt()
            notify(notificationId, builder.build())
        }
    }

    private fun initNotificationChannels() {
        initNewSongsChannel()
    }

    private fun initNewSongsChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Info about the channel
            val name = "New Uploaded Music"
            val descriptionText = "newly uploaded musics will be notified"
            val importance = NotificationManager.IMPORTANCE_DEFAULT

            // Create channel object
            val channel = NotificationChannel(NEW_SONGS_ID, name, importance).apply {
                description = descriptionText
            }

            // Tell the Android OS to create a channel
            notificationManager.createNotificationChannel(channel)
        }
    }


}