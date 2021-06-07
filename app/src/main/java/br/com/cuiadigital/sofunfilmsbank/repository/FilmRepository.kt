package br.com.cuiadigital.sofunfilmsbank.repository

import androidx.paging.DataSource
import br.com.cuiadigital.sofunfilmsbank.api.FilmRestApiTask
import br.com.cuiadigital.sofunfilmsbank.model.Film
import br.com.cuiadigital.sofunfilmsbank.model.FilmDetail

class FilmRepository(private val filmRestApiTask: FilmRestApiTask) {

    private val filmList = arrayListOf<Film>()
    private lateinit var filmDetail:FilmDetail

    fun getAllFilms(title: String):List<Film>{
        val request = filmRestApiTask.retrofitAPI().getAllFilms(title).execute()
        if (request.isSuccessful){
            request.body()?.let{
                filmList.clear()
                filmList.addAll(it.listFilms)
            }
        }
        return filmList
    }

    fun getFilmDetail(id:String):FilmDetail{
        val request = filmRestApiTask.retrofitAPI().getFilmDetail(id).execute()
        if (request.isSuccessful){
            request.body()?.let {
                filmDetail = it
            }
        }
        return filmDetail
    }
}