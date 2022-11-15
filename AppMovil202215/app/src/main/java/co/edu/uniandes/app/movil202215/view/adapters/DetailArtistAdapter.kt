package co.edu.uniandes.app.movil202215.view.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import co.edu.uniandes.app.movil202215.R
import co.edu.uniandes.app.movil202215.databinding.DetailArtistItemBinding
import co.edu.uniandes.app.movil202215.models.Artist
import co.edu.uniandes.app.movil202215.view.AlbumFragmentDirections
import co.edu.uniandes.app.movil202215.view.ArtistFragmentDirections
import com.squareup.picasso.Picasso

class DetailArtistAdapter : RecyclerView.Adapter<DetailArtistAdapter.CommentViewHolder>(){

    var detailArtist :List<Artist> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val withDataBinding: DetailArtistItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            CommentViewHolder.LAYOUT,
            parent,
            false)
        return CommentViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.artistDetail = detailArtist[position]
        }
        val currentItem = detailArtist[position]
        holder.bindImage(currentItem)
    }

    override fun getItemCount(): Int {
        return detailArtist.size
    }


    class CommentViewHolder(val viewDataBinding: DetailArtistItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.detail_artist_item
        }

        fun bindImage(artist: Artist){

            Picasso.get().load(artist.image).resize(100, 100)
                .centerCrop().into(viewDataBinding.imageDetail)
        }


    }


}
