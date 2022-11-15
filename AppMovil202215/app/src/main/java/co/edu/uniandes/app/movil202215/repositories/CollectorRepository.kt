package co.edu.uniandes.app.movil202215.repositories

import android.app.Application
import co.edu.uniandes.app.movil202215.models.Collector
import co.edu.uniandes.app.movil202215.network.NetworkServiceAdapter

class CollectorRepository (val application: Application) {
    suspend fun refreshData(): List<Collector> {
        return NetworkServiceAdapter.getInstance(application).getCollectors()
    }
}