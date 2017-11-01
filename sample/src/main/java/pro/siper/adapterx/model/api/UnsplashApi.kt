package pro.siper.adapterx.model.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface UnsplashApi {
    @GET("photos/?")
    fun listPhotos(@Query("client_id") client_id: String): Call<List<UnsplashItem>>
}