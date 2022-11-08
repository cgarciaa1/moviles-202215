package co.edu.uniandes.app.movil202215.repositories

import android.app.Application
import com.android.volley.VolleyError
import co.edu.uniandes.app.movil202215.models.Album
import co.edu.uniandes.app.movil202215.network.NetworkServiceAdapter

class AlbumRepository (val application: Application){
    suspend fun refreshData() : List<Album>{
        //Determinar la fuente de datos que se va a utilizar. Si es necesario consultar la red, ejecutar el siguiente código
       return NetworkServiceAdapter.getInstance(application).getAlbums()
     }
}