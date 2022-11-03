package co.edu.uniandes.app.movil202215.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import co.edu.uniandes.app.movil202215.models.Album
import co.edu.uniandes.app.movil202215.repositories.DetailAlbumRepository

class DetailAlbumViewModel(application: Application, albumId: Int) :  AndroidViewModel(application) {

    private val detailAlbumRepository = DetailAlbumRepository(application)

    private val _comments = MutableLiveData<List<Album>>()

    val comments: LiveData<List<Album>>
        get() = _comments

    private var _eventNetworkError = MutableLiveData<Boolean>(false)

    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)

    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    val id:Int = albumId

    init {
        refreshDataFromNetwork()
    }

    private fun refreshDataFromNetwork() {
        detailAlbumRepository.refreshData(id, {
            _comments.postValue(it)
            _eventNetworkError.value = false
            _isNetworkErrorShown.value = false
        },{
            Log.d("Error", it.toString())
            _eventNetworkError.value = true
        })
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

    class Factory(val app: Application, val albumId: Int) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(DetailAlbumViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return DetailAlbumViewModel(app, albumId) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}
