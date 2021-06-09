package br.com.cuiadigital.sofunfilmsbank.api

import br.com.cuiadigital.sofunfilmsbank.BuildConfig
import br.com.cuiadigital.sofunfilmsbank.model.FilmDetail
import br.com.cuiadigital.sofunfilmsbank.model.ListFilms
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FilmApi {
    @GET("?apikey=${BuildConfig.API_KEY}")
    fun getAllFilms(@Query(TITLE_PARAMETER) title:String): Call<ListFilms>

    @GET("?apikey=${BuildConfig.API_KEY}")
    fun getFilmDetail(@Query(ID_PARAMETER) title:String): Call<FilmDetail>

    companion object{
        const val TITLE_PARAMETER = "s"
        const val ID_PARAMETER = "i"
    }
}