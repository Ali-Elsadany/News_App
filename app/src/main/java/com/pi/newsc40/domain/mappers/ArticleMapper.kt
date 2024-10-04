package com.pi.newsc40.domain.mappers

import com.pi.newsc40.data.api.model.ArticleDM
import com.pi.newsc40.domain.model.Article
import javax.inject.Inject

class ArticleMapper @Inject constructor(){
     fun mapArticleDMToArticle(articleDM: ArticleDM): Article {
        return Article(
            imageUrl = articleDM.urlToImage ?: "",
            title = articleDM.title ?: "",
            author = articleDM.author ?: "",
            date = articleDM.publishedAt ?: ""
        )
    }

    fun mapArticlesDMToArticles(articles: List<ArticleDM>): List<Article> {
        return articles.map {
            mapArticleDMToArticle(it)
        }
    }
}