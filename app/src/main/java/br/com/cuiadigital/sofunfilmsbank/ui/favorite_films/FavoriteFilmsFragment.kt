package br.com.cuiadigital.sofunfilmsbank.ui.favorite_films

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.cuiadigital.sofunfilmsbank.R
import br.com.cuiadigital.sofunfilmsbank.databinding.FilmsFragmentBinding
import br.com.cuiadigital.sofunfilmsbank.databinding.FragmentFavoriteFilmsBinding
import br.com.cuiadigital.sofunfilmsbank.model.FilmDetail
import br.com.cuiadigital.sofunfilmsbank.ui.details_film.DetailFilmActivity
import br.com.cuiadigital.sofunfilmsbank.ui.films.FilmsAdapter
import br.com.cuiadigital.sofunfilmsbank.ui.films.FilmsFragment
import br.com.cuiadigital.sofunfilmsbank.ui.films.FilmsViewModel

class FavoriteFilmsFragment : Fragment(), FilmsFavoritesAdapter.FilmFavoriteClickItemListener {

    private var _binding: FragmentFavoriteFilmsBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: FavoriteFilmsViewModel
    private val filmsAdapter = FilmsFavoritesAdapter(this)

    companion object{
        val ID_EXTRA = "ID_FILM_FAVORITE_EXTRA"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteFilmsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleRecyclerview()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FavoriteFilmsViewModel::class.java)
        viewModel.init()
        initObserver()
    }

    private fun initObserver() {
        viewModel.filmDetail.observe(viewLifecycleOwner, { list->
            if (list.isNotEmpty()) {
                filmsAdapter.updateListFIlms(list)
            }
        })
    }

    override fun clickItemFavoriteFilm(film: FilmDetail?) {
        val intent = Intent(context, DetailFilmActivity::class.java)
        intent.putExtra(ID_EXTRA,film?.imdbID)
        startActivity(intent)
    }

    private fun handleRecyclerview() {
        binding.recyclerFavorites.apply {
            hasFixedSize()
            layoutManager = LinearLayoutManager(context)
            adapter = filmsAdapter
        }
    }


}