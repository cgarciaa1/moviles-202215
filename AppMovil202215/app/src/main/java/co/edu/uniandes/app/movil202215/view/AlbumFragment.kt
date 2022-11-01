package co.edu.uniandes.app.movil202215.view

import android.content.Intent
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
    private lateinit var albumList: ArrayList<AlbumModel>

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

        adapter.onItemClick = {
            val intent = Intent(context?.applicationContext,AlbumActivity::class.java)
            intent.putExtra("album", it)
            startActivity(intent)

            // startActivity(intent)
        }
    }

    private fun dataInitialize(){
        albumList = arrayListOf<AlbumModel>()
        // TODO: This is going to get used to fetch all the information from the DB
        albumList.add(AlbumModel(100,
            "Buscando América",
            "https://i.pinimg.com/564x/aa/5f/ed/aa5fed7fac61cc8f41d1e79db917a7cd.jpg",
            "1984-08-01",
            "Buscando América es el primer álbum de la banda de Rubén Blades y Seis del Solar lanzado en 1984. La producción, bajo el sello Elektra, fusiona diferentes ritmos musicales tales como la salsa, reggae, rock, y el jazz latino. El disco fue grabado en Eurosound Studios en Nueva York entre mayo y agosto de 1983.",
            "Salsa",
            "Elektra"))
        albumList.add(AlbumModel(100,
            "Buscando América",
            "https://i.pinimg.com/564x/aa/5f/ed/aa5fed7fac61cc8f41d1e79db917a7cd.jpg",
            "1984-08-01",
            "Buscando América es el primer álbum de la banda de Rubén Blades y Seis del Solar lanzado en 1984. La producción, bajo el sello Elektra, fusiona diferentes ritmos musicales tales como la salsa, reggae, rock, y el jazz latino. El disco fue grabado en Eurosound Studios en Nueva York entre mayo y agosto de 1983.",
            "Salsa",
            "Elektra"))
    }
}
