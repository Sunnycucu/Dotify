package edu.uw.sunny121.dotify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.ericchee.songdataprovider.Song
import com.ericchee.songdataprovider.SongDataProvider
import edu.uw.sunny121.dotify.databinding.ActivitySongListBinding
import android.content.Context

class SongListActivity : AppCompatActivity() {
    private val SONG_KEY = "SONG_KEY"
    private lateinit var binding : ActivitySongListBinding
    private lateinit var songObject : Song

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySongListBinding.inflate(layoutInflater).apply{setContentView(root)}

        with(binding) {
            if (savedInstanceState != null) {
                // recreating the activity because it has saveInstanceState
                var savedSong = savedInstanceState.getParcelable<Song>(SONG_KEY)
                if (savedSong != null) {
                    navigateToMainActivity(this@SongListActivity, savedSong)
                }
            }
            val adapter = SongListAdapter(SongDataProvider.getAllSongs())
            rvSongs.adapter = adapter

            miniPlayer.visibility = View.GONE
            adapter.onSongClickListner = {position, song ->
                miniPlayer.visibility = View.VISIBLE
                tvMini.text = song.title + " - " + song.artist
                songObject = song
            }
            miniPlayer.setOnClickListener {
                navigateToMainActivity(this@SongListActivity, songObject)
            }



            btnShuffle.setOnClickListener{
                adapter.updateSong(SongDataProvider.getAllSongs().toMutableList().shuffled())
            }
        }


    }

    override fun onSaveInstanceState(outState: Bundle) {
        // Save data
        outState.putParcelable(SONG_KEY, songObject)
        super.onSaveInstanceState(outState)
    }
}