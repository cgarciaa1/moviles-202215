package co.edu.uniandes.app.movil202215.repositories

import android.app.Application
import co.edu.uniandes.app.movil202215.models.Artist
import co.edu.uniandes.app.movil202215.network.NetworkServiceAdapter

class ArtistRepository (val application: Application){
    suspend fun refreshData() : List<Artist>{
        //Determinar la fuente de datos que se va a utilizar. Si es necesario consultar la red, ejecutar el siguiente código
       return NetworkServiceAdapter.getInstance(application).getArtists()
     }
}