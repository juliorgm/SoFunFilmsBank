@file:Suppress("PrivatePropertyName")

package br.com.cuiadigital.sofunfilmsbank

import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import br.com.cuiadigital.sofunfilmsbank.ui.SectionsPagerAdapter
import br.com.cuiadigital.sofunfilmsbank.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val TAB_SEARCH_FILMS = 0
    private val TAB_FAVORITE_FILMS = 1
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configureTabs()
    }

    private fun configureTabs() {
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.tabs
        tabs.setupWithViewPager(viewPager)
        tabs.getTabAt(TAB_SEARCH_FILMS)?.setIcon(R.drawable.ic_action_home)
        tabs.getTabAt(TAB_FAVORITE_FILMS)?.setIcon(R.drawable.ic_action_favorite)
    }
}