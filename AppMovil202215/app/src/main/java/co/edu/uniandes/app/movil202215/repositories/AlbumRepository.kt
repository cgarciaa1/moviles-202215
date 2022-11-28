package co.edu.uniandes.app.movil202215.repositories

import android.app.Application
import co.edu.uniandes.app.movil202215.models.Album
import co.edu.uniandes.app.movil202215.models.Track
import co.edu.uniandes.app.movil202215.network.NetworkServiceAdapter

class AlbumRepository (val application: Application){
    suspend fun refreshData() : List<Album>{
        //Determinar la fuente de datos que se va a utilizar. Si es necesario consultar la red, ejecutar el siguiente código
       return NetworkServiceAdapter.getInstance(application).getAlbums()
     }

    suspend fun createData(albumData: Album) : List<Album>{
        //Determinar la fuente de datos que se va a utilizar. Si es necesario consultar la red, ejecutar el siguiente código
        return NetworkServiceAdapter.getInstance(application).createAlbum(albumData)
    }
}
