package pumpkin.app.desafioredlink.data.dataSource

import pumpkin.app.desafioredlink.data.model.Album
import pumpkin.app.desafioredlink.data.model.Photo
import pumpkin.app.desafioredlink.data.model.Resource
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface WebService {
    @GET("albums")
    suspend fun getAllAlbums(): List<Album>
    @GET("albums/{id}/photos")
    suspend fun getAllPhotos(@Path("id") idr: String): List<Photo>
}