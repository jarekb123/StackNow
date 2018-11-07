package com.butajlo.stacknow.presentation.main

import androidx.navigation.findNavController
import com.butajlo.stacknow.R
import com.butajlo.stacknow.presentation.base.BaseActivity

class MainActivity : BaseActivity() {

    override val layoutRes = R.layout.activity_main

    override fun onBackPressed() {
        if(!findNavController(R.id.nav_host_fragment).navigateUp()) {
            super.onBackPressed()
        }
    }
}