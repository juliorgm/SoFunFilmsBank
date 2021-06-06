package br.com.cuiadigital.sofunfilmsbank.ui.films

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.cuiadigital.sofunfilmsbank.api.FilmRestApiTask
import br.com.cuiadigital.sofunfilmsbank.model.Film
import br.com.cuiadigital.sofunfilmsbank.repository.FilmRepository

class FilmsViewModel : ViewModel() {

    companion object{ const val TAG = "fudeu1" }

    private val filmRestApiTask = FilmRestApiTask()
    private val filmRepository = FilmRepository(filmRestApiTask)

    private var _filmList = MutableLiveData<List<Film>>()
    val filmList: LiveData<List<Film>>
    get() = _filmList

    var search_title = MutableLiveData<String>()

    fun init(){
        search_title.value = ""
    }

    fun search_films(){
        getAllFilms(search_title.value.toString())
    }

    private fun getAllFilms(title: String){
        Thread {
            try {
                val films = filmRepository.getAllFilms(title)
                _filmList.postValue(films)
            }catch (exception: Exception){
                Log.e(TAG, exception.message.toString())
            }
        }.start()
    }
}