package co.edu.uniandes.app.movil202215.models

data class Artist (
    val id:Int,
    val name:String,
    val image:String,
    val birthDate:String,
    val description:String,
    var albums: List<Album>,
)
