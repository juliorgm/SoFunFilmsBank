package br.com.cuiadigital.sofunfilmsbank.ui.details_film

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import br.com.cuiadigital.sofunfilmsbank.R
import br.com.cuiadigital.sofunfilmsbank.databinding.ActivityDetailFilmBinding
import br.com.cuiadigital.sofunfilmsbank.model.FilmDetail
import br.com.cuiadigital.sofunfilmsbank.model.Rating
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

        handleFavoriteButton()
    }

    private fun handleFavoriteButton() {
        binding.detailFavoriteImg.setOnClickListener{
            binding.detailFavoriteImg.setImageResource(R.drawable.ic_favorite_red)
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
        binding.detailTitle.text = film.title
        binding.detailPlot.text = film.plot
        binding.detaillActor.text = film.actors
        binding.detaillGenre.text = film.genre
        binding.detaillCountry.text = "${resources.getString(R.string.detail_header_country)}: ${film.country}"
        binding.detaillDirector.text = "${resources.getString(R.string.detail_header_director)}: ${film.director}"
        binding.detaillLanguage.text = "${resources.getString(R.string.detail_header_language)}: ${film.language}"
        binding.detaillWriter.text = "${resources.getString(R.string.detail_header_writer)}:  ${film.writer}"
        binding.detaillType.text = film.type

        handlePosterBinding(film.poster)
        handleRatingBinding(film.ratings)
    }

    private fun handleRatingBinding(ratings: List<Rating>) {
        if (ratings.size > EMPTY_ARRAY){
            binding.ratingBar.rating = getFloatRating(ratings)
        }else{
            binding.ratingBar.visibility = View.GONE
        }
    }

    private fun handlePosterBinding(poster: String) {
        if (poster.equals(NO_POSTER)){
            binding.detailPosterImg.load(R.drawable.generic_poster)
        }else{
            binding.detailPosterImg.load(poster){
                crossfade(true)
                placeholder(R.drawable.ic_film_placeholder)
            }
        }
    }


    private fun getFloatRating(rating: List<Rating>): Float {
        val arrayRating = rating[VALUE_RATING_POSTION].value
        val floatRating = arrayRating.split("/")[0].toFloat()
        return floatRating
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

    companion object{
        private val NO_POSTER ="N/A"
        private val VALUE_RATING_POSTION = 0
        private val EMPTY_ARRAY = 0
    }
}