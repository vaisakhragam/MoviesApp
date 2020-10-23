package com.example.moviesapplication.ui.main.view
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.moviesapplication.R
import com.example.moviesapplication.ui.main.adapter.MoviesPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_view_pager.*

const val ALL_MOVIES_PAGE_INDEX = 0
const val FAVORITES_PAGE_INDEX = 1
@AndroidEntryPoint

class HomeViewPagerFragment : Fragment() {


    private var sourcesView : View? = null
    var mContainerId:Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sourcesView = inflater.inflate(R.layout.fragment_view_pager, container, false)
        mContainerId = container?.id?:-1




        return  sourcesView
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view_pager.adapter = MoviesPagerAdapter(this)


        // Set the text for each tab


        TabLayoutMediator(tabs, view_pager) { tab, position ->

            tab.text = getTabTitle(position)

        }.attach()




    }

    private fun getTabTitle(position: Int): String? {
        return when (position) {
            ALL_MOVIES_PAGE_INDEX-> "All Movies"
            FAVORITES_PAGE_INDEX -> "Favorites"
            else -> null
        }
    }
}
