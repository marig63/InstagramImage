package com.marisa.guillaume.instagramunittest

import android.util.Log
import android.widget.EditText
import com.google.gson.JsonObject
import com.marisa.guillaume.instagramunittest.service.InstaImageService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun getInstaImages(activity: MainActivity, listener: InstaImagesListener){

    var httpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

    var retrofit = Retrofit.Builder()
            .client(httpClient)
            .baseUrl("https://apinsta.herokuapp.com/u/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    var storiesService = retrofit.create(InstaImageService::class.java)


    var name = activity.findViewById<EditText>(R.id.editText).text.toString()
    if(name == "") return

    val call = storiesService.getStories(name)
    call.enqueue(object: Callback<JsonObject> {
        override fun onFailure(call: Call<JsonObject>?, t: Throwable?) {
            Log.d("request media","failure")
        }

        override fun onResponse(call: Call<JsonObject>?, response: Response<JsonObject>?) {
            Log.d("request media","success")

            response?.body()?.let {

                if(it["graphql"] != null) {
                    var array = it["graphql"].asJsonObject["user"].asJsonObject["edge_owner_to_timeline_media"].asJsonObject["edges"].asJsonArray

                    //Log.d("src count", array.size().toString())

                    var max = if (array.size() >= 6) 6 else array.size()
                    var src = arrayListOf("aa", "aa", "aa", "aa", "aa", "aa")

                    for (i in 0..max - 1) {
                        src.set(i, array[i].asJsonObject["node"].asJsonObject["thumbnail_resources"].asJsonArray[2].asJsonObject["src"].asString)
                        Log.d("src $i", src[i])
                    }
                    listener.onImagesReceived(src)
                }
            }
        }

    })




}