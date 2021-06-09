package br.com.cuiadigital.sofunfilmsbank.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.cuiadigital.sofunfilmsbank.dao.FilmDetailDao
import br.com.cuiadigital.sofunfilmsbank.model.FilmDetail
import br.com.cuiadigital.sofunfilmsbank.ui.details_film.DetailFilmViewModel

@Database(entities = arrayOf(FilmDetail::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun filmDetailDao(): FilmDetailDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "filmdetail-database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
