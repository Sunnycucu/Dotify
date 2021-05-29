package edu.uw.sunny121.dotify

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.view.isVisible
import com.ericchee.songdataprovider.Song
import edu.uw.sunny121.dotify.databinding.ActivityMainBinding
import org.w3c.dom.Text
import kotlin.random.Random


fun navigateToMainActivity(context: Context, song: Song) = with(context) {
    val intent = Intent(this, MainActivity::class.java).apply {
        val bundle = Bundle().apply {
            putParcelable("song", song)
        }
        putExtras(bundle)
    }
    startActivity(intent)

}
class MainActivity : AppCompatActivity() {

    private val COUNT_PLAY_KEY = "COUNT_PLAY_KEY"
    //private var randomNumber = Random.nextInt(1000, 10000)
    private lateinit var binding : ActivityMainBinding
    lateinit var musicApp : MusicApplication
    private val increasePlayTimeManager: IncreasePlayTimeManager by lazy { musicApp.increasePlaytimeManger }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        musicApp = this.applicationContext as MusicApplication

                binding = ActivityMainBinding.inflate(layoutInflater).apply{setContentView(root)}
        with(binding) {

            if (savedInstanceState != null) {
                musicApp.count = savedInstanceState.getInt(COUNT_PLAY_KEY, 4321)
            }
            val song: Song? = intent.getParcelableExtra<Song>("song")
            tvTitle.text = song?.title
            tvArtist.text = song?.artist
            if(song?.largeImageID != null) {
                imageView3.setImageResource(song.largeImageID)
            }
            btSettings.setOnClickListener {
                if (song != null) {
                    nagivateToSettingsActivity(this@MainActivity, song)
                }

            }


            if (switch1 != null) {
                switch1.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
                    if (isChecked) {
                        increasePlayTimeManager.startIncreasePlaytimePeriodically()
                    } else {
                        increasePlayTimeManager.stopPeriodicallyIncreasing()
                    }
                })
            }

        }



        var tvNumberSongs = binding.tvNumberSongs

        tvNumberSongs.text = "${musicApp.count.toString()} plays"

        var ivPrev = binding.ivPrev
        var ivNext = binding.ivNext
//        var etUserName = binding.etUserName
//        var tvUserName = binding.tvUserName
//        var button = binding.button
        var imageView3 = binding.imageView3

//        etUserName.visibility = View.GONE




        imageView3.setOnLongClickListener {
            coverImageLongClicked()
        }

//        button.setOnClickListener{
//            if(button.text.toString() == "Apply") {
//                val userInputtedText = etUserName.text.toString()
//                if(userInputtedText != "") {
//                    tvUserName.text = userInputtedText
//                    etUserName.visibility = View.GONE
//                    tvUserName.visibility = View.VISIBLE
//                    button.text = "Change user"
//                }
//
//            } else {
//                etUserName.visibility = View.VISIBLE
//                tvUserName.visibility = View.GONE
//                button.text = "Apply"
//            }
//
//        }

        ivPrev.setOnClickListener{
            prevButtonClicked()
        }

        ivNext.setOnClickListener{
            nextButtonClicked()
        }
    }

    fun playButtonClicked(view: View) {
        musicApp.count++
        binding.tvNumberSongs.text = "${musicApp.count.toString()} plays"
    }
    
    private fun prevButtonClicked() {
        Toast.makeText(this,"Skipping to previous track", Toast.LENGTH_SHORT).show()

    }

    private fun nextButtonClicked() {
        Toast.makeText(this,"Skipping to next track", Toast.LENGTH_SHORT).show()
    }

    private fun coverImageLongClicked() : Boolean {
        binding.tvNumberSongs.setTextColor(Color.parseColor("#e65a8d"))
        return true
    }

    override fun onSaveInstanceState(outState: Bundle) {
        // Save data
        outState.putInt(COUNT_PLAY_KEY, musicApp.count)
        super.onSaveInstanceState(outState)
    }
}