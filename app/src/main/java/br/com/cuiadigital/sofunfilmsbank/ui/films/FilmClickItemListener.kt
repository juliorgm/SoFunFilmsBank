package br.com.cuiadigital.sofunfilmsbank.ui.films

import br.com.cuiadigital.sofunfilmsbank.model.Film

interface FilmClickItemListener {
    fun clickItemFilm(film: Film?)
}