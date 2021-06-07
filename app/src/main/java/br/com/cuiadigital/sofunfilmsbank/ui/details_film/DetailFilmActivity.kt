package br.com.cuiadigital.sofunfilmsbank.ui.details_film

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import br.com.cuiadigital.sofunfilmsbank.R
import br.com.cuiadigital.sofunfilmsbank.databinding.ActivityDetailFilmBinding
import br.com.cuiadigital.sofunfilmsbank.model.FilmDetail
import coil.load

class DetailFilmActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailFilmBinding
    private lateinit var viewModel: DetailFilmViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailFilmBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);

        if (intent.hasExtra("ID_FILM_EXTRA")){
            val idFilm = intent.getStringExtra("ID_FILM_EXTRA")
            viewModel = ViewModelProvider(this).get(DetailFilmViewModel::class.java)
            viewModel.init(idFilm)
            initObserver()
        }
    }

    private fun initObserver() {
        viewModel.filmDetail.observe(this,  { film->
            film?.let {
                loadViews(film)
            }
        })
    }

    private fun loadViews(film: FilmDetail) {
        binding.detailPosterImg.load(film.poster){
            placeholder(R.drawable.ic_film_placeholder)
            fallback(R.drawable.generic_poster)
        }
        binding.detailTitle.text = film.title
        binding.detailPlot.text = film.plot
        binding.detaillActor.text = film.actors
        binding.detaillGenre.text = film.genre
        binding.detaillCountry.text = "${resources.getString(R.string.detail_header_country)}: ${film.country}"
        binding.detaillDirector.text = "${resources.getString(R.string.detail_header_director)}: ${film.director}"
        binding.detaillLanguage.text = "${resources.getString(R.string.detail_header_language)}: ${film.language}"
        binding.detaillWriter.text = "${resources.getString(R.string.detail_header_writer)}:  ${film.writer}"
        binding.detaillType.text = film.type

        if (film.ratings.size>0){
            binding.ratingBar.rating = getRating(film.ratings[0].value)
        }else{
            binding.ratingBar.visibility = View.GONE
        }

    }

    private fun getRating(rating: String): Float {
        return rating.split("/")[0].toFloat()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}