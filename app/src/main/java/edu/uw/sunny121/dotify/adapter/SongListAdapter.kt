package edu.uw.sunny121.dotify.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ericchee.songdataprovider.Song
import edu.uw.sunny121.dotify.databinding.ItemMusicBinding

class SongListAdapter(private var listOfSong: List<Song>) : RecyclerView.Adapter<SongListAdapter.SongViewHolder>() {
    var onSongClickListner : (position : Int, song : Song) -> Unit = {_, _ ->}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val binding = ItemMusicBinding.inflate(LayoutInflater.from(parent.context))
        return SongViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val song = listOfSong[position]
        with(holder.binding) {
            tvArtistName.text = song.artist
            tvSongTitle.text = song.title
            ivAlbumCover.setImageResource(song.smallImageID)
            itemRoot.setOnClickListener {
                onSongClickListner(position, song)
            }
        }
    }


    override fun getItemCount(): Int = listOfSong.size

    fun updateSong(newListOfSong: List<Song>) {
        this.listOfSong = newListOfSong

        notifyDataSetChanged()
    }

    class SongViewHolder(val binding: ItemMusicBinding) : RecyclerView.ViewHolder(binding.root)
}
