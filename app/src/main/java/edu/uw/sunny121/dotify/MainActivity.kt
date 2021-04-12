package edu.uw.sunny121.dotify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import java.util.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var tvNumberSongs : TextView
    private var randomNumber = Random.nextInt(1000, 10000)
    private lateinit var ivPrev: ImageView
    private lateinit var ivNext: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvNumberSongs = findViewById<TextView>(R.id.tvNumberSongs)

        tvNumberSongs.text = "$randomNumber plays"

        ivPrev = findViewById(R.id.ivPrev)
        ivNext = findViewById(R.id.ivNext)

        ivPrev.setOnClickListener{
            prevButtonClicked()
        }

        ivNext.setOnClickListener{
            nextButtonClicked()
        }
    }

    fun playButtonClicked(view: View) {
        randomNumber += 1
        tvNumberSongs.text = "$randomNumber plays"
    }
    
    fun prevButtonClicked() {
        Toast.makeText(this,"Skipping to previous track", Toast.LENGTH_SHORT).show()
        Log.i("echhe", "clicked prev")
    }

    fun nextButtonClicked() {
        Toast.makeText(this,"Skipping to next track", Toast.LENGTH_SHORT).show()
    }
}