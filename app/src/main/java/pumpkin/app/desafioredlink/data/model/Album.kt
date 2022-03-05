package pumpkin.app.desafioredlink.data.model

import com.google.gson.annotations.SerializedName

data class Album(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("title")
    val title: String? = null

)
