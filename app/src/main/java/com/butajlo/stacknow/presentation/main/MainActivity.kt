package com.butajlo.stacknow.presentation.main

import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.navigation.findNavController
import com.butajlo.stacknow.R
import com.butajlo.stacknow.presentation.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import timber.log.Timber

class MainActivity : BaseActivity() {

    override val layoutRes = R.layout.activity_main

    private val searchManager: SearchManager by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)

        if(Intent.ACTION_SEARCH == intent.action) {
            intent.getStringExtra(SearchManager.QUERY)?.also {
                Timber.d("Search query: $it")
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)

        (menu.findItem(R.id.menu_search).actionView as SearchView).apply {
            maxWidth = Int.MAX_VALUE
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
            setIconifiedByDefault(true)
        }
        return true
    }

    override fun onBackPressed() {
        if(!findNavController(R.id.nav_host_fragment).navigateUp()) {
            super.onBackPressed()
        }
    }
}