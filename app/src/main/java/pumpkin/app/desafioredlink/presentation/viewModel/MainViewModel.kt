package pumpkin.app.desafioredlink.presentation.viewModel

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import pumpkin.app.desafioredlink.data.model.Resource
import pumpkin.app.desafioredlink.data.repository.Repository
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository) : ViewModel() {


    fun getAlbums() = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(repository.getAlbumsList())
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }

    private val _idToSearch = MutableLiveData<String>()

    fun setIdToSearch(id: String){
        _idToSearch.postValue(id)
    }

    val idtoSearch = _idToSearch.distinctUntilChanged().switchMap {
        liveData(Dispatchers.IO) {
            emit(Resource.Loading())
            try {
                emit(repository.getAllPhotosInRequestedAlbum(_idToSearch.value!!))
            } catch (e: Exception) {
                emit(Resource.Failure(e))
            }
        }
    }

}