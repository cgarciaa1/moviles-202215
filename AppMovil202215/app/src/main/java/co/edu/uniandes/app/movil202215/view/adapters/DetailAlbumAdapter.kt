package co.edu.uniandes.app.movil202215.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import co.edu.uniandes.app.movil202215.R
import co.edu.uniandes.app.movil202215.databinding.DetailAlbumItemBinding
import co.edu.uniandes.app.movil202215.models.Album
import com.squareup.picasso.Picasso

class DetailAlbumAdapter : RecyclerView.Adapter<DetailAlbumAdapter.CommentViewHolder>(){

    var detailAlbum :List<Album> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val withDataBinding: DetailAlbumItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            CommentViewHolder.LAYOUT,
            parent,
            false)
        return CommentViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.albumDetail = detailAlbum[position]
        }
        val currentItem = detailAlbum[position]
        holder.bindImage(currentItem)
    }

    override fun getItemCount(): Int {
        return detailAlbum.size
    }


    class CommentViewHolder(val viewDataBinding: DetailAlbumItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.detail_album_item
        }

        fun bindImage(album: Album){

            Picasso.get().load(album.cover).placeholder(R.drawable.image_loader).resize(100, 100)
                .centerCrop().into(viewDataBinding.coverDetail)
        }


    }


}