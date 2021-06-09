package br.com.cuiadigital.sofunfilmsbank.ui.details_film

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import br.com.cuiadigital.sofunfilmsbank.api.FilmRestApiTask
import br.com.cuiadigital.sofunfilmsbank.database.AppDatabase
import br.com.cuiadigital.sofunfilmsbank.model.FilmDetail
import br.com.cuiadigital.sofunfilmsbank.repository.FilmDetailRepository
import kotlinx.coroutines.launch

class DetailFilmViewModel(application: Application) : AndroidViewModel(Application()) {
    companion object{ const val TAG = "DetailFilmViewModel" }

    private val database = AppDatabase.getInstance(application.applicationContext)
    private val filmRestApiTask = FilmRestApiTask()
    private val filmDetailRepository = FilmDetailRepository(database.filmDetailDao(),filmRestApiTask)

    private var _filmDetail = MutableLiveData<FilmDetail?>()
    val filmDetail: LiveData<FilmDetail?>
        get() = _filmDetail

    private var _isFavorite= MutableLiveData<Boolean>()
    val isFavorite: LiveData<Boolean>
        get() = _isFavorite

    private var idFilm = MutableLiveData<String>()

    fun init(idFilm: String?) {
        this.idFilm.value = idFilm
        getDetailFilm()
    }


    fun favoriteChangeState(){
        _isFavorite.value = isFavorite.value != true

        if (isFavorite.value == true){
            saveFilmDetail()
        }else{
            deleteFilmDetail()
        }
    }

    private fun saveFilmDetail() {
        Thread {
            try {
                filmDetail.value?.let { filmDetailRepository.insert(filmDetail = it) }
            } catch (exception: Exception) {
                Log.e(TAG, exception.message.toString())
            }
        }.start()
    }

    private fun deleteFilmDetail() {
        Thread {
            try {
                filmDetail.value?.let { filmDetailRepository.delete(filmDetail = it) }
            } catch (exception: Exception) {
                Log.e(TAG, exception.message.toString())
            }
        }.start()
    }

    private fun getDetailFilm(){
        Thread {
            try {
                val id = idFilm.value.toString()
                val film = filmDetailRepository.getFilmDetail(id)
                _filmDetail.postValue(film)
            }catch (exception: Exception){
                Log.e(TAG, exception.message.toString())
            }
        }.start()
    }

    fun createFilmDetail(film: FilmDetail) =
        viewModelScope.launch {
            filmDetailRepository.insert(filmDetail = filmDetail.value as FilmDetail)
        }
}