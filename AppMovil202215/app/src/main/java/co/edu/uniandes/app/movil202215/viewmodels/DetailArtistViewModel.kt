package co.edu.uniandes.app.movil202215.viewmodels

import android.app.Application
import androidx.lifecycle.*
import co.edu.uniandes.app.movil202215.models.Artist
import co.edu.uniandes.app.movil202215.repositories.DetailArtistRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailArtistViewModel(application: Application, artistId: Int) :  AndroidViewModel(application) {

    private val detailArtistRepository = DetailArtistRepository(application)

    private val _artistDetail = MutableLiveData<List<Artist>>()

    val comments: LiveData<List<Artist>>
        get() = _artistDetail

    private var _eventNetworkError = MutableLiveData(false)

    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData(false)

    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    val id:Int = artistId

    init {
        refreshDataFromNetwork()
    }


    private fun refreshDataFromNetwork() {
        try {
            viewModelScope.launch  (Dispatchers.Default){
                withContext(Dispatchers.IO){
                    val data = detailArtistRepository.refreshData(id)
                    _artistDetail.postValue(data)
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

    class Factory(val app: Application, private val artistId: Int) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(DetailArtistViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return DetailArtistViewModel(app, artistId) as T
            }
            throw IllegalArgumentException("Unable to construct view-model")
        }
    }
}
