package co.edu.uniandes.app.movil202215.viewmodels

import android.app.Application
import androidx.lifecycle.*
import co.edu.uniandes.app.movil202215.models.Track
import co.edu.uniandes.app.movil202215.models.Album
import co.edu.uniandes.app.movil202215.repositories.DetailAlbumRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class CreateTrackViewModel(application: Application) :  AndroidViewModel(application) {

    private val detailAlbum = DetailAlbumRepository(application)

    private val _response = MutableLiveData<Boolean>()

    private var _eventNetworkError = MutableLiveData(false)

    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData(false)

    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    fun createObject(album:Album, track:Track) {
        try {
            viewModelScope.launch  (Dispatchers.Default){
                withContext(Dispatchers.IO){

                    detailAlbum.addSong(album, track)

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

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(CreateTrackViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return CreateTrackViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct view-model")
        }
    }
}
