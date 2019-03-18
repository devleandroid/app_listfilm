package android.br.com.lebronx.kodeapp.Interface

import android.br.com.lebronx.kodeapp.Model.DataResult
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiInterface {

    @GET("volley_array.json")
    fun getResults(): Call<List<DataResult>>

    companion object {
        //Api para teste http://35.200.174.74/apis/volley_array.json
        //Api vortigo https://vortigo.blob.core.windows.net/files/pokemon/data/types.json
        var BASE_URL = "http://35.200.174.74/apis/"

        fun create(): ApiInterface{
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiInterface::class.java)
        }
    }
}