package co.edu.uniandes.app.movil202215.repositories

import android.app.Application
import co.edu.uniandes.app.movil202215.models.Collector
import co.edu.uniandes.app.movil202215.network.NetworkServiceAdapter

class DetailCollectorRepository (val application: Application){
    suspend fun refreshData(collectorId: Int): List<Collector>{
        var collector = NetworkServiceAdapter.getInstance(application).getCollectorById(collectorId)
        collector[0].collectorAlbums = NetworkServiceAdapter.getInstance(application).getCollectorAlbums(collectorId)
        return  collector
    }
}