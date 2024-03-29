package br.com.cuiadigital.sofunfilmsbank.ui.films

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import br.com.cuiadigital.sofunfilmsbank.R
import br.com.cuiadigital.sofunfilmsbank.databinding.FilmsFragmentBinding
import br.com.cuiadigital.sofunfilmsbank.model.Film
import br.com.cuiadigital.sofunfilmsbank.ui.details_film.DetailFilmActivity

class FilmsFragment : Fragment(), FilmsAdapter.FilmClickItemListener {
    private var _binding: FilmsFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: FilmsViewModel
    private val filmsAdapter = FilmsAdapter(this)

    companion object{
        val ID_EXTRA = "ID_FILM_EXTRA"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FilmsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        messageToView(R.string.message_do_search)
        initClickButton()
        initTitleSearch()
        handleRecyclerview()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FilmsViewModel::class.java)
        viewModel.init()
        initObserver()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initClickButton() {
        binding.imageButton.setOnClickListener {
            loadingVisibity(true)
            binding.progressBar.visibility = View.VISIBLE
            viewModel.searchTitle.value = binding.edtSearchTitle.text.toString()
            val searchTitle: String = viewModel.searchTitle.value.toString()

            if (searchTitle.isEmpty()){
                messageToView(R.string.message_do_search)
            }else{
                viewModel.searchFilms()
                updateScreen()
            }
        }
    }

    private fun initTitleSearch() {
        binding.edtSearchTitle.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(titleSearch: Editable?) {
                if (!titleSearch.isNullOrEmpty()) {
                    viewModel.searchTitle.postValue(titleSearch.toString())
                }
            }
        })
    }

    private fun handleRecyclerview() {
        binding.recyclerFilms.apply {
            hasFixedSize()
            adapter = filmsAdapter
        }
    }

    private fun initObserver() {
        viewModel.filmList.observe(viewLifecycleOwner,  { list->
            if (list.isNotEmpty()) {
                filmsAdapter.updateListFIlms(list)
                searchSucefful()
            }
        })
    }

    private fun updateScreen() {
        val listUpdatedFilms = viewModel.filmList.value
        if (!listUpdatedFilms.isNullOrEmpty()) {
            filmsAdapter.updateListFIlms(listUpdatedFilms)
        } else {
            loadingVisibity(false)
            messageToView(R.string.message_empty_search)
        }
    }

    private fun loadingVisibity(isLoading: Boolean){
        binding.progressBar.visibility = if(isLoading) View.VISIBLE else View.GONE
    }

    private fun messageToView(message: Int){
        binding.progressBar.visibility = View.GONE
        binding.recyclerFilms.visibility = View.GONE
        binding.txtMessage.visibility = View.VISIBLE
        binding.txtMessage.text = resources.getString(message)
    }

    private fun searchSucefful() {
        binding.progressBar.visibility = View.GONE
        binding.recyclerFilms.visibility = View.VISIBLE
        binding.txtMessage.visibility = View.GONE
    }

    override fun clickItemFilm(film: Film) {
        val intent = Intent(context, DetailFilmActivity::class.java)
        intent.putExtra(ID_EXTRA,film.id)
        startActivity(intent)
    }
}