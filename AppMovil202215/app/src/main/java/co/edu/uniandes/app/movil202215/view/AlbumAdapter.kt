package co.edu.uniandes.app.movil202215.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import co.edu.uniandes.app.movil202215.R
import com.google.android.material.imageview.ShapeableImageView
import org.w3c.dom.Text

class AlbumAdapter(private val albumList: ArrayList<Album>): RecyclerView.Adapter<AlbumAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_layout, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AlbumAdapter.ViewHolder, position: Int) {
        val currentItem = albumList[position]
        holder.itemTitle.text = currentItem.title
        holder.itemDetail.text = currentItem.detail
        holder.itemImage.setImageURI(currentItem.image.toUri())
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
