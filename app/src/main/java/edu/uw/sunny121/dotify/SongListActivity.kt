package edu.uw.sunny121.dotify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.ericchee.songdataprovider.SongDataProvider
import edu.uw.sunny121.dotify.databinding.ActivitySongListBinding

class SongListActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySongListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySongListBinding.inflate(layoutInflater).apply{setContentView(root)}

        with(binding) {
            val adapter = SongListAdapter(SongDataProvider.getAllSongs())
            rvSongs.adapter = adapter

            miniPlayer.visibility = View.GONE
            adapter.onSongClickListner = {position, song ->
                miniPlayer.visibility = View.VISIBLE
                tvMini.text = song.title + " - " + song.artist
            }

            btnShuffle.setOnClickListener{
                adapter.updateSong(SongDataProvider.getAllSongs().toMutableList().shuffled())
            }
        }


    }
}