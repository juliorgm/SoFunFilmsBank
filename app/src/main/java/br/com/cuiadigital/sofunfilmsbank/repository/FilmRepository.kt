package br.com.cuiadigital.sofunfilmsbank.repository

import android.util.Log
import br.com.cuiadigital.sofunfilmsbank.api.FilmRestApiTask
import br.com.cuiadigital.sofunfilmsbank.model.Film

class FilmRepository(private val filmRestApiTask: FilmRestApiTask) {

    private val filmList = arrayListOf<Film>()

    fun getAllFilms(title: String):List<Film>{
        val request = filmRestApiTask.retrofitAPI().getAllFilms(title).execute()
        if (request.isSuccessful){
            request.body()?.let{
                filmList.clear()
                filmList.addAll(it.listFilms)
            }
        }else{
            Log.e("fudeu2",request.message())
        }
        return filmList
    }
}