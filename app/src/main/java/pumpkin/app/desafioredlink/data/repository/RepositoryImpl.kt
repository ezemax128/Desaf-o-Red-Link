package pumpkin.app.desafioredlink.data.repository

import pumpkin.app.desafioredlink.data.dataSource.DataSurce
import pumpkin.app.desafioredlink.data.model.Album
import pumpkin.app.desafioredlink.data.model.Photo
import pumpkin.app.desafioredlink.data.model.Resource
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val dataSource: DataSurce) : Repository {
    override suspend fun getAlbumsList(): Resource<List<Album>> {
        return dataSource.getAllAlbums()
    }

    override suspend fun getAllPhotosInRequestedAlbum(id:String): Resource<List<Photo>> {
        return dataSource.getAllPhotos(id)
    }
}