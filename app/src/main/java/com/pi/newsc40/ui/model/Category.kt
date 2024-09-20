package com.pi.newsc40.ui.model

import com.pi.newsc40.R

data class Category(
    val id: String,
    val imageId: Int,
    val backgroundColorId: Int,
    val title: String,
    val isLeftSided: Boolean,
) {
    companion object {
        val categories = listOf(
            Category(
                id = "sports", imageId = R.drawable.sports,
                backgroundColorId = R.color.red, title = "Sports", true
            ),
            Category(
                id = "entertainment", imageId = R.drawable.politics,
                backgroundColorId = R.color.blue, title = "Entertainment", false
            ),
            Category(
                id = "health", imageId = R.drawable.health,
                backgroundColorId = R.color.pink, title = "Health", true
            ),
            Category(
                id = "business", imageId = R.drawable.bussines,
                backgroundColorId = R.color.orange, title = "Business", false
            ),
            Category(
                id = "technology", imageId = R.drawable.environment,
                backgroundColorId = R.color.light_blue, title = "Technology", true
            ),
            Category(
                id = "science", imageId = R.drawable.science,
                backgroundColorId = R.color.yellow, title = "Science", false
            ),
        )
    }
}