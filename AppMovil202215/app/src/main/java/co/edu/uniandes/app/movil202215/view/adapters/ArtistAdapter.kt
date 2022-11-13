package co.edu.uniandes.app.movil202215.view.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import co.edu.uniandes.app.movil202215.R
import co.edu.uniandes.app.movil202215.databinding.ArtistItemBinding
import co.edu.uniandes.app.movil202215.models.Artist
import com.squareup.picasso.Picasso

class ArtistAdapter : RecyclerView.Adapter<ArtistAdapter.ArtistViewHolder>(){

    var artists :List<Artist> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistViewHolder {
        val withDataBinding: ArtistItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            ArtistViewHolder.LAYOUT,
            parent,
            false)
        return ArtistViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.artist = artists[position]
        }
        val currentItem = artists[position]
        holder.bindImage(currentItem)

    //fragmento para agregar la navegacion hacia el detalle del artista
    /**    holder.viewDataBinding.root.setOnClickListener {
            val action = ArtistFragmentDirections.actionAlbumFragmentToCommentFragment(artists[position].id)
            // Navigate using that action
            holder.viewDataBinding.root.findNavController().navigate(action)

        }
    **/
    }

    override fun getItemCount(): Int {
        return artists.size
    }


    class ArtistViewHolder(val viewDataBinding: ArtistItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.artist_item
        }

        fun bindImage(artist: Artist){

            Picasso.get().load(artist.image).resize(100, 100)
                .centerCrop().into(viewDataBinding.image)
        }
    }


}