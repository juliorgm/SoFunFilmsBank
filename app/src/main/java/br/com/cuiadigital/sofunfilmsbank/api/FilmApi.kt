package br.com.cuiadigital.sofunfilmsbank.api

import android.os.Build
import br.com.cuiadigital.sofunfilmsbank.BuildConfig
import br.com.cuiadigital.sofunfilmsbank.model.Film
import br.com.cuiadigital.sofunfilmsbank.model.ListFilms
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FilmApi {
    @GET("?apikey=${BuildConfig.API_KEY}")
    fun getAllFilms(@Query("s") title:String): Call<ListFilms>
}