package co.edu.uniandes.app.movil202215.models

data class Collector (
    val id:Int,
    val name:String,
    val telephone:String,
    val email:String,
    val albumsCount:Int,
    var collectorAlbums: List<CollectorAlbum>
)
