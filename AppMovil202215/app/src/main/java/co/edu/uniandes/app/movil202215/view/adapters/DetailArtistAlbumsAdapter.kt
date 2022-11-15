package co.edu.uniandes.app.movil202215.view.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.RecyclerView
import co.edu.uniandes.app.movil202215.R
import co.edu.uniandes.app.movil202215.databinding.AlbumItemBinding
import co.edu.uniandes.app.movil202215.models.Album
import co.edu.uniandes.app.movil202215.view.AlbumFragmentDirections
import co.edu.uniandes.app.movil202215.view.ArtistFragmentDirections
import co.edu.uniandes.app.movil202215.view.MainActivity
import com.squareup.picasso.Picasso


class DetailArtistAlbumsAdapter : RecyclerView.Adapter<DetailArtistAlbumsAdapter.AlbumViewHolder>(){

    var albums :List<Album> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val withDataBinding: AlbumItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            AlbumViewHolder.LAYOUT,
            parent,
            false)
        return AlbumViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.album = albums[position]
        }
        val currentItem = albums[position]
        holder.bindImage(currentItem)
    }

    override fun getItemCount(): Int {
        return albums.size
    }


    class AlbumViewHolder(val viewDataBinding: AlbumItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.album_item
        }

        fun bindImage(album: Album){

            Picasso.get().load(album.cover).resize(100, 100)
                .centerCrop().into(viewDataBinding.cover)
        }
    }


}
