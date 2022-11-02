package co.edu.uniandes.app.movil202215.repositories

import android.app.Application
import co.edu.uniandes.app.movil202215.models.Album
import com.android.volley.VolleyError
import co.edu.uniandes.app.movil202215.network.NetworkServiceAdapter

class DetailAlbumRepository (val application: Application){
    fun refreshData(albumId: Int, callback: (List<Album>)->Unit, onError: (VolleyError)->Unit) {
        //Determinar la fuente de datos que se va a utilizar. Si es necesario consultar la red, ejecutar el siguiente código
        NetworkServiceAdapter.getInstance(application).getAlbumById(albumId,{
            //Guardar los coleccionistas de la variable it en un almacén de datos local para uso futuro
            callback(it)
        },
            onError
        )
    }
}