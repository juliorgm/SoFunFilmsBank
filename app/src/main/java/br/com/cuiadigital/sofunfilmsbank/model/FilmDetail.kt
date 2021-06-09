package br.com.cuiadigital.sofunfilmsbank.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity()
data class FilmDetail(
    @SerializedName("Title")
    val title : String,
    @SerializedName("Year")
    val year : String,
    @SerializedName("Rated")
    val rated : String,
    @SerializedName("Released")
    val released : String,
    @SerializedName("Runtime")
    val runtime : String,
    @SerializedName("Genre")
    val genre : String,
    @SerializedName("Director")
    val director : String,
    @SerializedName("Writer")
    val writer : String,
    @SerializedName("Actors")
    val actors : String,
    @SerializedName("Plot")
    val plot : String,
    @SerializedName("Language")
    val language : String,
    @SerializedName("Country")
    val country : String,
    @SerializedName("Awards")
    val awards : String,
    @SerializedName("Poster")
    val poster : String,
    @SerializedName("Metascore")
    val metascore : String,
    @SerializedName("imdbRating")
    val imdbRating : String,
    @SerializedName("imdbVotes")
    val imdbVotes : String,
    @SerializedName("imdbID")
    @PrimaryKey
    val imdbID : String,
    @SerializedName("Type")
    val type : String,
) : Parcelable

@Parcelize
data class Rating(
    @SerializedName("Source")
    val source : String,
    @SerializedName("Value")
    val value : String,
) : Parcelable