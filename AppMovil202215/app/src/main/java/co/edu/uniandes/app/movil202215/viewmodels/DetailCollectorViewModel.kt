package co.edu.uniandes.app.movil202215.viewmodels

import android.app.Application
import androidx.lifecycle.*
import co.edu.uniandes.app.movil202215.models.Collector
import co.edu.uniandes.app.movil202215.repositories.DetailCollectorRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailCollectorViewModel (application: Application, collectorId: Int) :  AndroidViewModel(application) {

    private val detailCollectorRepository = DetailCollectorRepository(application)

    private val _collectorDetail = MutableLiveData<List<Collector>>()

    val comments: LiveData<List<Collector>>
        get() = _collectorDetail

    private var _eventNetworkError = MutableLiveData(false)

    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData(false)

    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    val id:Int = collectorId

    init {
        refreshDataFromNetwork()
    }


    private fun refreshDataFromNetwork() {
        try {
            viewModelScope.launch  (Dispatchers.Default){
                withContext(Dispatchers.IO){
                    val data = detailCollectorRepository.refreshData(id)
                    _collectorDetail.postValue(data)
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

    class Factory(val app: Application, private val collectorId: Int) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(DetailCollectorViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return DetailCollectorViewModel(app, collectorId) as T
            }
            throw IllegalArgumentException("Unable to construct view-model")
        }
    }
}
