package edu.uw.sunny121.dotify

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.net.toUri
import androidx.lifecycle.lifecycleScope
import coil.load
import com.ericchee.songdataprovider.Song
import edu.uw.sunny121.dotify.databinding.FragmentProfileBinding
import edu.uw.sunny121.dotify.databinding.FragmentSettingsBinding
import edu.uw.sunny121.dotify.databinding.FragmentStatisticsBinding
import edu.uw.sunny121.dotify.model.Userprofile
import kotlinx.coroutines.launch
import java.lang.System.load


class ProfileFragment : Fragment() {

    private lateinit var myApp : MusicApplication
    private val dataRepository by lazy { myApp.dataRepository }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        myApp = context.applicationContext as MusicApplication
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentProfileBinding.inflate(inflater)

        with(binding) {
            lifecycleScope.launch {
                runCatching {
                    val up : Userprofile = dataRepository.getUserProfile()
                    tvProfileName.text = up.username
                    tvFavIce.text = "platform : ${up.platform}"
                    ivWoody.load(up.profilePicURL)
                    tvEmail.text = "${up.firstName} ${up.lastName}"
                }
            }

            return binding.root
        }




    }
}