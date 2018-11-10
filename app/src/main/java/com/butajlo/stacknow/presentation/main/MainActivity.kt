package com.butajlo.stacknow.presentation.main

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import com.butajlo.stacknow.R
import com.butajlo.stacknow.presentation.base.BaseActivity
import com.butajlo.stacknow.presentation.di.ACTIVITY_SCOPE
import com.butajlo.stacknow.presentation.search.SearchFragment
import com.butajlo.stacknow.presentation.toolbar.ToolbarProvider
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.android.scope.ext.android.bindScope
import org.koin.android.scope.ext.android.getOrCreateScope

class MainActivity : BaseActivity() {

    override val layoutRes = R.layout.activity_main

    private val toolbarProvider: ToolbarProvider by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)

        bindScope(getOrCreateScope(ACTIVITY_SCOPE))
        toolbarProvider.toolbar = toolbar
        toolbarProvider.navController = findNavController(R.id.nav_host_fragment)
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