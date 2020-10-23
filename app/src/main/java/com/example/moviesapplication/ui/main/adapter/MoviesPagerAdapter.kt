package com.example.moviesapplication.ui.main.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.moviesapplication.ui.main.view.FavoritesFragment
import com.example.moviesapplication.ui.main.view.MoviesFragment


const val MOVIES_PAGE_INDEX = 0
const val FAVORITES_PAGE_INDEX = 1

class MoviesPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    /**
     * Mapping of the ViewPager page indexes to their respective Fragments
     */
    private val tabFragmentsCreators: Map<Int, () -> Fragment> = mapOf(
        MOVIES_PAGE_INDEX to { MoviesFragment() },
        FAVORITES_PAGE_INDEX to { FavoritesFragment() }
    )

    override fun getItemCount() = tabFragmentsCreators.size

    override fun createFragment(position: Int): Fragment {
        return tabFragmentsCreators[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }
}
