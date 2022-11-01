package co.edu.uniandes.app.movil202215.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import co.edu.uniandes.app.movil202215.R
import com.squareup.picasso.Picasso
import org.w3c.dom.Text

class AlbumActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_layout)

        val album = intent.getParcelableExtra<AlbumModel>("album")
        if (album != null) {
            val title : TextView = findViewById(R.id.detail_layout_title)
            val description : TextView = findViewById(R.id.detail_layout_description)
            val detail : TextView = findViewById(R.id.detail_layout_detail)
            val image : ImageView = findViewById(R.id.detail_layout_image)

            title.text = album.name
            description.text = album.description
            detail.text = "${album.genre} — ${album.releaseDate} — ${album.recordLabel} "
            Picasso.get().load(album.cover).placeholder(R.drawable.image_loader).resize(100, 100).centerCrop().into(image);
        }
    }
}
