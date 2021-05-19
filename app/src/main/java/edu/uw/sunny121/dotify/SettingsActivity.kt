package edu.uw.sunny121.dotify

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.ericchee.songdataprovider.Song
import edu.uw.sunny121.dotify.databinding.ActivitySettingsBinding

private const val SONG_KEY = "song"
private const val PLAYTIME_KEY = "playtime"

fun nagivateToSettingsActivity(context: Context, song: Song) = with(context) {
    startActivity(Intent(this, SettingsActivity::class.java).apply {
        putExtra(SONG_KEY, song)
    })

}
class SettingsActivity : AppCompatActivity() {

    private val navController by lazy { findNavController(R.id.navHost) }

    private lateinit var binding: ActivitySettingsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater).apply { setContentView(root) }
        with(binding) {
            navController.setGraph(R.navigation.nav_graph, intent.extras)
        }
//        navController.setGraph(R.navigation.nav_graph, Bundle().apply {
//            putInt("numOfPosts", 50)
//        })

        //setupActionBarWithNavController(navController)

    }
}