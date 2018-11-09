package com.butajlo.stacknow.presentation.search.results

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.butajlo.stacknow.R
import com.butajlo.stacknow.domain.entity.QuestionEntity
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*

data class QuestionBinding(
    val title: String,
    val username: String,
    val avatarUrl: String?,
    val answersCount: Int,
    val date: String,
    val tags: List<String>
)

fun QuestionEntity.toBinding() = QuestionBinding(
    title = title,
    username = owner.username,
    avatarUrl = owner.profileImageUrl,
    answersCount = answersCount,
    date = let {
        val format = SimpleDateFormat("MMMM d, yyyy h:mm a", Locale.US)
        format.format(creationDate.time)
    },
    tags = tags
)

@BindingAdapter("imageUrl")
fun ImageView.bindImage(url: String?) {
    Picasso.get()
        .load(url)
        .error(R.drawable.ic_avatar_placeholder)
        .into(this)
}