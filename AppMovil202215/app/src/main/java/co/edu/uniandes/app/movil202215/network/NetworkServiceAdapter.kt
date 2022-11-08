package co.edu.uniandes.app.movil202215.network

import android.content.Context
import android.util.Log
import co.edu.uniandes.app.movil202215.models.Album
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

    val customTimeout = 15000

    companion object{
        const val BASE_URL= "https://back-vinilos.herokuapp.com/"
        var instance: NetworkServiceAdapter? = null
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
                        description = album.getString("description"), tracks = arrayOf()))
                }

                cont.resume(list)
            },
            {
                Log.e("REST API - VOLLEY", "Error encontrado: "+it)
                cont.resumeWithException(it)
            }))
    }



    suspend fun getAlbumById(albumId:Int) = suspendCoroutine<List<Album>>{ cont->
        requestQueue.add(getRequest("albums/$albumId",
            { response ->
                val list = mutableListOf<Album>()

                var item = JSONObject(response)
                var tracksArray :JSONArray =  item.getJSONArray("tracks")
                val trackList = mutableListOf<Track>()

                for (j in 0 until tracksArray.length()) {
                    var itemtrack:JSONObject = tracksArray.getJSONObject(j)
                    trackList.add(j, Track(trackId = itemtrack.getInt("id"),name = itemtrack.getString("name"),
                        duration = itemtrack.getString("duration")))
                }


                list.add(Album(albumId = item.getInt("id"),name = item.getString("name"),
                    cover = item.getString("cover"), recordLabel = item.getString("recordLabel"),
                    releaseDate = item.getString("releaseDate"), genre = item.getString("genre"),
                    description = item.getString("description"),  tracks = trackList.toTypedArray()))


                cont.resume(list)
            },
            {
                Log.e("REST API - VOLLEY", "Error encontrado: "+it)
                cont.resumeWithException(it)
            }))
    }

    private fun getRequest(path:String, responseListener: Response.Listener<String>, errorListener: Response.ErrorListener): StringRequest {
        var request = StringRequest(Request.Method.GET, BASE_URL+path, responseListener,errorListener)
        request.retryPolicy = DefaultRetryPolicy(customTimeout,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)
        return request
    }
    private fun postRequest(path: String, body: JSONObject,  responseListener: Response.Listener<JSONObject>, errorListener: Response.ErrorListener ):JsonObjectRequest{
        return  JsonObjectRequest(Request.Method.POST, BASE_URL+path, body, responseListener, errorListener)
    }
}