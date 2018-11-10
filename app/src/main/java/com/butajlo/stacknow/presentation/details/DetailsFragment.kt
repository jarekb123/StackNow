package com.butajlo.stacknow.presentation.details

import android.os.Bundle
import android.view.View
import com.butajlo.stacknow.R
import com.butajlo.stacknow.presentation.base.BaseFragment
import com.butajlo.stacknow.presentation.ext.argument
import com.butajlo.stacknow.presentation.toolbar.ToolbarProvider
import kotlinx.android.synthetic.main.screen_details.*
import org.koin.android.ext.android.inject

class DetailsFragment : BaseFragment() {

    override val layoutRes = R.layout.screen_details

    private val toolbarProvider by inject<ToolbarProvider>()
    private val detailsUrl by argument<String>(ARGUMENT_DETAILS_URL)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        toolbarProvider.toolbar?.run {
            setNavigationIcon(R.drawable.ic_back)
            setNavigationOnClickListener { toolbarProvider.back() }
        }
        showDetails()
    }

    private fun showDetails() {
        detailsUrl?.run {
            wv_details.loadUrl(this)
        }
    }

    companion object {
        const val ARGUMENT_DETAILS_URL = "ARGUMENT_DETAILS_URL"
    }

}