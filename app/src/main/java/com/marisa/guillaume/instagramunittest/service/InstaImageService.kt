package com.marisa.guillaume.instagramunittest.service

import com.google.gson.JsonObject
import com.marisa.guillaume.instagramunittest.MainActivity
import com.marisa.guillaume.instagramunittest.model.Stories
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface InstaImageService {

    @GET("users/self/media/recent")
    fun getRecentMedia(@Query("access_token") token: String): Call<JsonObject>

    @GET("{name}/")
    fun getStories( @Path("name") name: String): Call<JsonObject>

}