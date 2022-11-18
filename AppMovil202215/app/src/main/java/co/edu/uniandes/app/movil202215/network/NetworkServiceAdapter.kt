package co.edu.uniandes.app.movil202215.network

import android.content.Context
import android.util.Log
import co.edu.uniandes.app.movil202215.models.Album
import co.edu.uniandes.app.movil202215.models.Artist
import co.edu.uniandes.app.movil202215.models.Collector
import co.edu.uniandes.app.movil202215.models.Track
import com.android.volley.*
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class NetworkServiceAdapter constructor(context: Context) {

    private val customTimeout = 15000

    companion object{
        const val BASE_URL= "https://back-vinilos.herokuapp.com/"
        private var instance: NetworkServiceAdapter? = null
        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: NetworkServiceAdapter(context).also {
                    instance = it
                }
            }
    }
    private val requestQueue: RequestQueue by lazy {
        // applicationContext keeps you from leaking the Activity or BroadcastReceiver if someone passes one in.
        Volley.newRequestQueue(context.applicationContext)
    }
    suspend fun getAlbums() = suspendCoroutine<List<Album>>{ cont->
        requestQueue.add(getRequest("albums",
            { response ->
                val resp = JSONArray(response)
                val list = mutableListOf<Album>()
                for (i in 0 until resp.length()) {

                    val album = resp.getJSONObject(i)

                    list.add(i, Album(albumId = album.getInt("id"),name = album.getString("name"),
                        cover = album.getString("cover"), recordLabel = album.getString("recordLabel"),
                        releaseDate = album.getString("releaseDate"), genre = album.getString("genre"),
                        description = album.getString("description"), tracks = listOf()))
                }

                cont.resume(list)
            },
            {
                Log.e("REST API - VOLLEY", "Error encontrado: $it")
                cont.resumeWithException(it)
            }))
    }

    suspend fun getAlbumById(albumId:Int) = suspendCoroutine<List<Album>>{ cont->
        requestQueue.add(getRequest("albums/$albumId",
            { response ->
                val list = mutableListOf<Album>()

                val item = JSONObject(response)
                val tracksArray :JSONArray =  item.getJSONArray("tracks")
                val trackList = mutableListOf<Track>()

                for (j in 0 until tracksArray.length()) {
                    val itemtrack:JSONObject = tracksArray.getJSONObject(j)
                    trackList.add(j, Track(trackId = itemtrack.getInt("id"),name = itemtrack.getString("name"),
                        duration = itemtrack.getString("duration")))
                }


                list.add(Album(albumId = item.getInt("id"),name = item.getString("name"),
                    cover = item.getString("cover"), recordLabel = item.getString("recordLabel"),
                    releaseDate = item.getString("releaseDate"), genre = item.getString("genre"),
                    description = item.getString("description"),  tracks = trackList))


                cont.resume(list)
            },
            {
                Log.e("REST API - VOLLEY", "Error encontrado: $it")
                cont.resumeWithException(it)
            }))
    }

    suspend fun getArtists() = suspendCoroutine<List<Artist>>{ cont->
        requestQueue.add(getRequest("musicians",
            { response ->
                val resp = JSONArray(response)
                val list = mutableListOf<Artist>()
                for (i in 0 until resp.length()) {

                    val album = resp.getJSONObject(i)

                    val albumsArray :JSONArray =  album.getJSONArray("albums")
                    val albumList = mutableListOf<Album>()

                    for (j in 0 until albumsArray.length()) {
                        val albumItem:JSONObject = albumsArray.getJSONObject(j)

                        albumList.add(Album(albumId = albumItem.getInt("id"),name = albumItem.getString("name"),
                            cover = albumItem.getString("cover"), recordLabel = albumItem.getString("recordLabel"),
                            releaseDate = albumItem.getString("releaseDate"), genre = albumItem.getString("genre"),
                            description = albumItem.getString("description"),  tracks = listOf()))
                    }

                    list.add(i, Artist(artistId = album.getInt("id"),name = album.getString("name"),
                        image = album.getString("image"), birthDate = album.getString("birthDate"),

                        description = album.getString("description"), albums =albumList))
                }

                cont.resume(list)
            },
            {
                Log.e("REST API - VOLLEY", "Error encontrado: $it")
                cont.resumeWithException(it)
            }))
    }


    suspend fun getArtistById(artistId:Int) = suspendCoroutine<List<Artist>>{ cont->
        requestQueue.add(getRequest("musicians/$artistId",
            { response ->
                val list = mutableListOf<Artist>()

                val item = JSONObject(response)
                val albumArray :JSONArray =  item.getJSONArray("albums")
                val albumList = mutableListOf<Album>()

                for (j in 0 until albumArray.length()) {
                    val itemAlbum:JSONObject = albumArray.getJSONObject(j)
                    albumList.add(j, Album(albumId = itemAlbum.getInt("id"),name = itemAlbum.getString("name"),
                        cover = itemAlbum.getString("cover"), recordLabel = itemAlbum.getString("recordLabel"),
                        releaseDate = itemAlbum.getString("releaseDate"), genre = itemAlbum.getString("genre"),
                        description = itemAlbum.getString("description"), tracks = listOf())
                    )
                }


                list.add(Artist(artistId = item.getInt("id"),name = item.getString("name"),
                    image = item.getString("image"), description = item.getString("description"),
                    birthDate = item.getString("birthDate").dropLast(14), albums = albumList))
                
                cont.resume(list)
            },
            {
                Log.e("REST API - VOLLEY", "Error encontrado: $it")
                cont.resumeWithException(it)
            }))
    }

    suspend fun getCollectors() = suspendCoroutine<List<Collector>> { cont->
        requestQueue.add(getRequest("collectors",
            { response ->
                val resp = JSONArray(response)
                val list = mutableListOf<Collector>()
                for (i in 0 until resp.length()) {

                    val collector = resp.getJSONObject(i)

                    val collectorAlbums :JSONArray =  collector.getJSONArray("collectorAlbums")

                    list.add(i, Collector(id = collector.getInt("id"), name = collector.getString("name"),
                        telephone = collector.getString("telephone"), email = collector.getString("email"),
                        albumsCount = collectorAlbums.length()))
                }

                cont.resume(list)
            },
            {
                Log.e("REST API - VOLLEY", "Error encontrado: $it")
                cont.resumeWithException(it)
            }))
    }

    suspend fun createAlbum(albumData:Album) = suspendCoroutine<List<Album>>{ cont->
        Log.d("", "Llegooooooo"+ this)


        val parameters = JSONObject()

        parameters.put("name",albumData.name);
        parameters.put("cover",albumData.cover);
        parameters.put("releaseDate",albumData.releaseDate);
        parameters.put("description",albumData.description);
        parameters.put("genre",albumData.genre);
        parameters.put("recordLabel",albumData.recordLabel);

        requestQueue.add(postRequest("albums", parameters,
            { response ->



                cont.resume( listOf())
            },
            {
                Log.e("REST API - VOLLEY", "Error encontrado: $it")
                cont.resumeWithException(it)
            }))
    }


    private fun getRequest(path:String, responseListener: Response.Listener<String>, errorListener: Response.ErrorListener): StringRequest {
        val request = StringRequest(Request.Method.GET, BASE_URL+path, responseListener,errorListener)
        request.retryPolicy = DefaultRetryPolicy(customTimeout,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)
        return request
    }
    private fun postRequest(path: String, body: JSONObject,  responseListener: Response.Listener<JSONObject>, errorListener: Response.ErrorListener ):JsonObjectRequest{
        Log.d("", "Llegooooooo aqui"+ this)
        return  JsonObjectRequest(Request.Method.POST, BASE_URL+path, body, responseListener, errorListener)
    }
}
