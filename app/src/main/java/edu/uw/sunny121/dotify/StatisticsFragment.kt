package edu.uw.sunny121.dotify

import android.content.Context
import  android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ericchee.songdataprovider.Song
import edu.uw.sunny121.dotify.databinding.FragmentStatisticsBinding


class StatisticsFragment : Fragment() {


    private val safeArgs: StatisticsFragmentArgs by navArgs()
    private val navController by lazy { findNavController() }
    private lateinit var musicApp : MusicApplication

    override fun onAttach(context: Context) {
        super.onAttach(context)
        musicApp = context.applicationContext as MusicApplication
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentStatisticsBinding.inflate(inflater)

        val receivedSong : Song = safeArgs.song
        val receivedPlaytime : String = musicApp.count.toString()
        with(binding) {
            ivAlbum.setImageResource(receivedSong.largeImageID)
            tvPlayCount.text = "Play count : $receivedPlaytime"
        }

        return binding.root
    }
}