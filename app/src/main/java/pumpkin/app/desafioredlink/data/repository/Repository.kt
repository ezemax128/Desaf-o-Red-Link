package pumpkin.app.desafioredlink.data.repository

import pumpkin.app.desafioredlink.data.model.Album
import pumpkin.app.desafioredlink.data.model.Photo
import pumpkin.app.desafioredlink.data.model.Resource

interface Repository {
    suspend fun getAlbumsList(): Resource<List<Album>>
    suspend fun getAllPhotosInRequestedAlbum(id:String): Resource<List<Photo>>
}