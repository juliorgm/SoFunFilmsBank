package br.com.cuiadigital.sofunfilmsbank.ui

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import br.com.cuiadigital.sofunfilmsbank.R
import br.com.cuiadigital.sofunfilmsbank.ui.favorite_films.FavoriteFilmsFragment
import br.com.cuiadigital.sofunfilmsbank.ui.films.FilmsFragment

private val TAB_TITLES = arrayOf(
    R.string.tab_text_1,
    R.string.tab_text_2
)
private val TAB_FAVORITES_FILMS = 1
private val NUM_TABS = 2

class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        when(position){
            TAB_FAVORITES_FILMS -> return FavoriteFilmsFragment()
            else -> return FilmsFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        return NUM_TABS
    }
}