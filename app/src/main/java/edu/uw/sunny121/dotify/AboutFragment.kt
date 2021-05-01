package edu.uw.sunny121.dotify

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import edu.uw.sunny121.dotify.databinding.FragmentAboutBinding
import androidx.navigation.fragment.navArgs


class AboutFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentAboutBinding.inflate(inflater)

        with(binding) {
            var version = BuildConfig.VERSION_NAME
            tvVersion.text = "Version : $version"
        }
        return binding.root
    }


}