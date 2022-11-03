package co.edu.uniandes.app.movil202215.models

import co.edu.uniandes.app.movil202215.view.TrackModel

data class Album (
    val albumId:Int,
    val name:String,
    val cover:String,
    val releaseDate:String,
    val description:String,
    val genre:String,
    val recordLabel:String,
    var tracks: Array<Track>,
)
