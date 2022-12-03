package co.edu.uniandes.app.movil202215.view.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import co.edu.uniandes.app.movil202215.R
import co.edu.uniandes.app.movil202215.databinding.DetailCollectorAlbumItemBinding
import co.edu.uniandes.app.movil202215.models.CollectorAlbum

class DetailCollectorAlbumsAdapter: RecyclerView.Adapter<DetailCollectorAlbumsAdapter.CommentViewHolder>(){

    var detailCollectorAlbum :List<CollectorAlbum> = emptyList()
    @SuppressLint("NotifyDataSetChanged")
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val withDataBinding: DetailCollectorAlbumItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            CommentViewHolder.LAYOUT,
            parent,
            false)
        return CommentViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.collectorAlbum = detailCollectorAlbum[position]
        }
    }

    override fun getItemCount(): Int {
        return detailCollectorAlbum.size
    }


    class CommentViewHolder(val viewDataBinding: DetailCollectorAlbumItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.detail_collector_album_item
        }
    }
}
