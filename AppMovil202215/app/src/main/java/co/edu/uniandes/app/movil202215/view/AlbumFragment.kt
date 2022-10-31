package co.edu.uniandes.app.movil202215.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.edu.uniandes.app.movil202215.R

class AlbumFragment : Fragment() {

    private lateinit var adapter : AlbumAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var albumList: ArrayList<Album>

    private lateinit var image: Array<String>
    private lateinit var title : Array<String>
    private lateinit var detail: Array<String>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_albums, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataInitialize()
        val layoutManager = LinearLayoutManager(context)
        recyclerView = view.findViewById(R.id.recycler_view_albums_list)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        adapter = AlbumAdapter(albumList)
        recyclerView.adapter = adapter
    }

    private fun dataInitialize(){
        albumList = arrayListOf<Album>()

        // TODO: This information will be updated with the API call when integrating.
        title = arrayOf("Buscando América", "Poeta del pueblo", "A Night at the Opera", "A Day at the Races")
        detail = arrayOf("Salsa", "Salsa", "Rock", "Rock")
        image = arrayOf("https://i.pinimg.com/564x/aa/5f/ed/aa5fed7fac61cc8f41d1e79db917a7cd.jpg", "https://cdn.shopify.com/s/files/1/0275/3095/products/image_4931268b-7acf-4702-9c55-b2b3a03ed999_1024x1024.jpg", "https://upload.wikimedia.org/wikipedia/en/4/4d/Queen_A_Night_At_The_Opera.png", "https://www.udiscovermusic.com/wp-content/uploads/2019/11/a-day-at-the-races.jpg")

        for (i in image.indices) {
            val album = Album(title[i], detail[i], image[i])
            albumList.add(album)
        }
    }
}
