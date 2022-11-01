package co.edu.uniandes.app.movil202215.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import co.edu.uniandes.app.movil202215.R
import com.squareup.picasso.Picasso

class AlbumAdapter(private val albumList: ArrayList<AlbumModel>): RecyclerView.Adapter<AlbumAdapter.ViewHolder>() {

    var onItemClick: ((AlbumModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_layout, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AlbumAdapter.ViewHolder, position: Int) {
        val currentItem = albumList[position]
        holder.itemTitle.text = currentItem.name
        holder.itemDetail.text = currentItem.genre
        Picasso.get().load(currentItem.cover).placeholder(R.drawable.image_loader).resize(100, 100).centerCrop().into(holder.itemImage);

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(currentItem)
        }
    }

    override fun getItemCount(): Int {
        return albumList.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var itemTitle: TextView = itemView.findViewById(R.id.card_view_layout_text_title)
        var itemDetail: TextView = itemView.findViewById(R.id.card_view_layout_text_detail)
        var itemImage: ImageView = itemView.findViewById(R.id.card_view_image)

    }
}
