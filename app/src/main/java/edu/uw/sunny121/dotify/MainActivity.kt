package edu.uw.sunny121.dotify

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.view.isVisible
import edu.uw.sunny121.dotify.databinding.ActivityMainBinding
import org.w3c.dom.Text
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var tvNumberSongs : TextView
    private var randomNumber = Random.nextInt(1000, 10000)
    private lateinit var ivPrev: ImageView
    private lateinit var ivNext: ImageView
    private lateinit var etUserName : EditText
    private lateinit var tvUserName : TextView
    private lateinit var button : Button
    private lateinit var imageView3 : ImageView


    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply{setContentView(root)}

        setContentView(R.layout.activity_main)
        tvNumberSongs = findViewById(R.id.tvNumberSongs)

        tvNumberSongs.text = "$randomNumber plays"

        ivPrev = findViewById(R.id.ivPrev)
        ivNext = findViewById(R.id.ivNext)
        etUserName = findViewById(R.id.etUserName)
        tvUserName = findViewById(R.id.tvUserName)
        button = findViewById(R.id.button)
        imageView3 = findViewById(R.id.imageView3)

        etUserName.visibility = View.GONE



        imageView3.setOnLongClickListener {
            coverImageLongClicked()
        }

        button.setOnClickListener{
            if(button.text.toString() == "Apply") {
                val userInputtedText = etUserName.text.toString()
                if(userInputtedText != "") {
                    tvUserName.text = userInputtedText
                    etUserName.visibility = View.GONE
                    tvUserName.visibility = View.VISIBLE
                    button.text = "Change user"
                }

            } else {
                etUserName.visibility = View.VISIBLE
                tvUserName.visibility = View.GONE
                button.text = "Apply"
            }

        }

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
        //Log.i("echhe", "clicked prev")
    }

    fun nextButtonClicked() {
        Toast.makeText(this,"Skipping to next track", Toast.LENGTH_SHORT).show()
    }

    fun coverImageLongClicked() : Boolean {
        tvNumberSongs.setTextColor(Color.parseColor("#e65a8d"))
        return true
    }
}