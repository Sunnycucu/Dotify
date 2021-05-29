package edu.uw.sunny121.dotify

import android.app.Application
import edu.uw.sunny121.dotify.repository.DataRepository
import kotlin.random.Random

class MusicApplication: Application() {

    lateinit var dataRepository : DataRepository
    lateinit var increasePlaytimeManger: IncreasePlayTimeManager
    lateinit var notificationManager :  songNotificationManager

    var count : Int = Random.nextInt(1000, 10000);


    override fun onCreate() {
        super.onCreate()
        this.increasePlaytimeManger = IncreasePlayTimeManager(this)
        this.notificationManager = songNotificationManager(this)
        dataRepository = DataRepository()
    }
}