package br.com.cuiadigital.sofunfilmsbank.ui.films

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import br.com.cuiadigital.sofunfilmsbank.R
import br.com.cuiadigital.sofunfilmsbank.databinding.FilmsFragmentBinding
import br.com.cuiadigital.sofunfilmsbank.model.Film

class FilmsFragment : Fragment() {
    private var _binding: FilmsFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: FilmsViewModel
    private val filmsAdapter = FilmsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FilmsFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
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
            viewModel.search_title.value = binding.edtSearchTitle.text.toString()
            val search_title: String = viewModel.search_title.value.toString()

            if (search_title.isNullOrEmpty()){
                messageToView(R.string.message_do_search)
            }else{
                viewModel.search_films()
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
                    viewModel.search_title.postValue(titleSearch.toString())
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
            messageToView(R.string.message_empty_search)
        }
    }

    private fun loadingVisibity(isLoading: Boolean){
        binding.progressBar.visibility = if(isLoading) View.VISIBLE else View.GONE
    }

    fun messageToView(message: Int){
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
}