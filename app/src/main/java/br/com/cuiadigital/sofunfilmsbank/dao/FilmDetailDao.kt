package br.com.cuiadigital.sofunfilmsbank.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import br.com.cuiadigital.sofunfilmsbank.model.FilmDetail

@Dao
interface FilmDetailDao {
    @Query("SELECT * FROM filmdetail")
    fun getAll(): List<FilmDetail>

    @Query("SELECT * FROM filmdetail WHERE imdbID IN (:FilmDetailImdbIDs)")
    fun getFilmDetailByIds(FilmDetailImdbIDs: String):FilmDetail

    @Insert
    fun insert(filmDetail: FilmDetail)

    @Delete
    fun delete(user: FilmDetail)
}