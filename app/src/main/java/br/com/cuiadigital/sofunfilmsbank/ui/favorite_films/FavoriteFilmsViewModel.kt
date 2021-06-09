package br.com.cuiadigital.sofunfilmsbank.ui.favorite_films

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.cuiadigital.sofunfilmsbank.database.AppDatabase
import br.com.cuiadigital.sofunfilmsbank.model.FilmDetail
import br.com.cuiadigital.sofunfilmsbank.repository.FilmDetailRepository
import br.com.cuiadigital.sofunfilmsbank.ui.details_film.DetailFilmViewModel

class FavoriteFilmsViewModel(application: Application) : AndroidViewModel(Application()) {
    private val database = AppDatabase.getInstance(application.applicationContext)
    private val filmDetailRepository = FilmDetailRepository(database.filmDetailDao(),null)

    private var _filmDetail = MutableLiveData<List<FilmDetail?>>()
    val filmDetail: LiveData<List<FilmDetail?>>
        get() = _filmDetail

    fun init(){
        getDetailFilm()
    }

    private fun getDetailFilm(){
        Thread {
            try {
                val film = filmDetailRepository.allFilmDetail()
                _filmDetail.postValue(film)
            }catch (exception: Exception){
                Log.e(DetailFilmViewModel.TAG, exception.message.toString())
            }
        }.start()
    }
}