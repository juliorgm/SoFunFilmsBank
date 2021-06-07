package br.com.cuiadigital.sofunfilmsbank.ui.details_film

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.cuiadigital.sofunfilmsbank.api.FilmRestApiTask
import br.com.cuiadigital.sofunfilmsbank.model.FilmDetail
import br.com.cuiadigital.sofunfilmsbank.repository.FilmRepository

class DetailFilmViewModel : ViewModel() {
    companion object{ const val TAG = "DetailFilmViewModel" }

    private val filmRestApiTask = FilmRestApiTask()
    private val filmRepository = FilmRepository(filmRestApiTask)

    private var _filmDetail = MutableLiveData<FilmDetail>()
    val filmDetail: LiveData<FilmDetail>
        get() = _filmDetail

    var idFilm = MutableLiveData<String>()

    fun init(idFilm: String?) {
        this.idFilm.value = idFilm
        getDetailFilm()
    }

    fun getFilmDetail(){
        getDetailFilm()
    }

    private fun getDetailFilm(){
        Thread {
            try {
                val id = idFilm.value.toString()
                val film = filmRepository.getFilmDetail(id)
                _filmDetail.postValue(film)
            }catch (exception: Exception){
                Log.e(TAG, exception.message.toString())
            }
        }.start()
    }
}