package br.com.cuiadigital.sofunfilmsbank.ui.details_film

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.cuiadigital.sofunfilmsbank.R

class DetailFilmFragment : Fragment() {

    companion object {
        fun newInstance() = DetailFilmFragment()
    }

    private lateinit var viewModel: DetailFilmViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail_film, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailFilmViewModel::class.java)
        // TODO: Use the ViewModel
    }

}