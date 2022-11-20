package co.edu.uniandes.app.movil202215.models

data class Artist (
    val artistId:Int,
    val name:String,
    val image:String,
    val description:String,
    val birthDate:String,
    var albums: List<Album>,
)

