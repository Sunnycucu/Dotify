package edu.uw.sunny121.dotify

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ericchee.songdataprovider.Song
import edu.uw.sunny121.dotify.databinding.FragmentSettingsBinding


class SettingsFragment : Fragment() {

    private val safeArgs: SettingsFragmentArgs by navArgs()

    private val navController by lazy { findNavController() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentSettingsBinding.inflate(inflater)


        with(binding) {
            val receivedSong : Song = safeArgs.song
            btProfile.setOnClickListener {
                navController.navigate(R.id.profileFragment)
            }

            btStatistic.setOnClickListener {
                navController.navigate(SettingsFragmentDirections.actionSettingsFragmentToStatisticsFragment(receivedSong))
            }

            btAbout.setOnClickListener {
                navController.navigate(R.id.aboutFragment)
            }

        }
        return binding.root

    }
}