package br.com.cuiadigital.sofunfilmsbank.repository

import androidx.annotation.WorkerThread
import br.com.cuiadigital.sofunfilmsbank.api.FilmApi
import br.com.cuiadigital.sofunfilmsbank.api.FilmRestApiTask
import br.com.cuiadigital.sofunfilmsbank.dao.FilmDetailDao
import br.com.cuiadigital.sofunfilmsbank.model.FilmDetail

class FilmDetailRepository(val filmDetailDao: FilmDetailDao?, val filmRestApiTask: FilmRestApiTask?) {

    fun allFilmDetail(): List<FilmDetail> {
            return filmDetailDao?.getAll()!!
    }

    @Suppress("RedundantSuspendModifier")
    fun insert(filmDetail: FilmDetail) {
        filmDetailDao?.insert(filmDetail)
    }

    fun delete(filmDetail: FilmDetail) {
        filmDetailDao?.delete(filmDetail)
    }

    fun getFilmDetail(imdbID: String):FilmDetail? {
        val filmDetail = filmDetailDao?.getFilmDetailByIds(imdbID)
        if (filmDetail !=null){
            return filmDetail
        }else{
            val request = filmRestApiTask?.retrofitAPI()?.getFilmDetail(imdbID)?.execute()
            if (request?.isSuccessful!!){
                request?.body()?.let {
                    return it
                }
            }
            return null
        }
    }
}