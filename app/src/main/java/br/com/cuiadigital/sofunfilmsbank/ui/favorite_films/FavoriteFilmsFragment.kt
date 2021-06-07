package br.com.cuiadigital.sofunfilmsbank.ui.favorite_films

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.cuiadigital.sofunfilmsbank.R

class FavoriteFilmsFragment : Fragment() {

    companion object {
        fun newInstance() = FavoriteFilmsFragment()
    }

    private lateinit var viewModel: FavoriteFilmsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite_films, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FavoriteFilmsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}