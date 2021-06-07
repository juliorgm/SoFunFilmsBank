package br.com.cuiadigital.sofunfilmsbank.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Film(
    @SerializedName("Title")
val title : String,
@SerializedName("Year")
val year : String,
@SerializedName("imdbID")
val id : String,
@SerializedName("Type")
val type : String,
@SerializedName("Poster")
val poster : String
) : Parcelable