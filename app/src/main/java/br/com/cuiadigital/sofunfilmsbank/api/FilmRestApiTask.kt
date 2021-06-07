package br.com.cuiadigital.sofunfilmsbank.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FilmRestApiTask {
    companion object{
        const val BASE_URL = "https://www.omdbapi.com/"
    }

    private fun filmProvider():Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun retrofitAPI():FilmApi = filmProvider().create(FilmApi::class.java)
}