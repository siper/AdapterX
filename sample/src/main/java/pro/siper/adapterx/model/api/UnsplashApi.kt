package pro.siper.adapterx.model.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface UnsplashApi {
    @GET("photos/?")
    fun listPhotos(@Query("client_id") clientId: String,
                   @Query("per_page") perPage: Int = 100): Call<List<UnsplashItem>>
}