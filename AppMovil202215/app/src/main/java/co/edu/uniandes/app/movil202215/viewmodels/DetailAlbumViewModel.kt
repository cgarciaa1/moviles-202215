package co.edu.uniandes.app.movil202215.viewmodels

import android.app.Application
import androidx.lifecycle.*
import co.edu.uniandes.app.movil202215.models.Album
import co.edu.uniandes.app.movil202215.repositories.DetailAlbumRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailAlbumViewModel(application: Application, albumId: Int) :  AndroidViewModel(application) {

    private val detailAlbumRepository = DetailAlbumRepository(application)

    private val _albumDetail = MutableLiveData<List<Album>>()

    val comments: LiveData<List<Album>>
        get() = _albumDetail

    private var _eventNetworkError = MutableLiveData(false)

    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData(false)

    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    val id:Int = albumId

    init {
        refreshDataFromNetwork()
    }


    private fun refreshDataFromNetwork() {
        try {
            viewModelScope.launch  (Dispatchers.Default){
                withContext(Dispatchers.IO){
                    val data = detailAlbumRepository.refreshData(id)
                    _albumDetail.postValue(data)
                }
                _eventNetworkError.postValue(false)
                _isNetworkErrorShown.postValue(false)
            }
        }
        catch (e:Exception){
            _eventNetworkError.value = true
        }
    }


    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

    class Factory(val app: Application, private val albumId: Int) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(DetailAlbumViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return DetailAlbumViewModel(app, albumId) as T
            }
            throw IllegalArgumentException("Unable to construct view-model")
        }
    }
}
