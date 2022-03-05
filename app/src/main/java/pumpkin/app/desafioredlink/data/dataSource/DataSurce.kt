package pumpkin.app.desafioredlink.data.dataSource

import pumpkin.app.desafioredlink.data.model.Album
import pumpkin.app.desafioredlink.data.model.Photo
import pumpkin.app.desafioredlink.data.model.Resource
import javax.inject.Inject

class DataSurce @Inject constructor(private val webService: WebService) {

    suspend fun getAllAlbums(): Resource<List<Album>> {
        return Resource.Success(webService.getAllAlbums())
    }

    suspend fun getAllPhotos(id: String): Resource<List<Photo>>{
        return Resource.Success(webService.getAllPhotos(id))
    }
}