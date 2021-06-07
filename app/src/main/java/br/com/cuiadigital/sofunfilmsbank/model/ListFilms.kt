package br.com.cuiadigital.sofunfilmsbank.model

import com.google.gson.annotations.SerializedName

data class ListFilms (
    @SerializedName("Search")
    val listFilms: List<Film>
)