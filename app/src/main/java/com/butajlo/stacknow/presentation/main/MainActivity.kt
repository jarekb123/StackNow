package com.butajlo.stacknow.presentation.main

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import com.butajlo.stacknow.R
import com.butajlo.stacknow.presentation.base.BaseActivity
import com.butajlo.stacknow.presentation.search.SearchFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.search_screen.*

class MainActivity : BaseActivity() {

    override val layoutRes = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)

        (menu.findItem(R.id.menu_search).actionView as SearchView).also {
            it.maxWidth = Int.MAX_VALUE
            it.setIconifiedByDefault(true)
            it.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    findNavController(R.id.nav_host_fragment)
                        .navigate(
                            R.id.action_searchFragment_self,
                            bundleOf(SearchFragment.ARGUMENT_SEARCH_QUERY to query)
                        )
                    it.clearFocus()
                    return false
                }

                override fun onQueryTextChange(newText: String?) = false
            })
        }
        return true
    }

    override fun onBackPressed() {
        if (!findNavController(R.id.nav_host_fragment).navigateUp()) {
            super.onBackPressed()
        }
    }
}