package edu.uw.sunny121.dotify

import android.app.Application
import edu.uw.sunny121.dotify.repository.DataRepository
import kotlin.random.Random

class MusicApplication: Application() {

    lateinit var dataRepository : DataRepository
    var count : Int = Random.nextInt(1000, 10000);


    override fun onCreate() {
        super.onCreate()
        dataRepository = DataRepository()
    }
}