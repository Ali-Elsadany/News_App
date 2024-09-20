package com.pi.newsc40.ui.screens.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.pi.newsc40.R
import com.pi.newsc40.data.api.model.Article
import com.pi.newsc40.databinding.ItemNewsBinding
import com.pi.newsc40.ui.screens.home.HomeActivity

class NewsAdapter(var articles: List<Article?>) : Adapter<NewsAdapter.NewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding: ItemNewsBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_news, parent, false
        )
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article = articles[position]
        holder.binding.article = article
//        holder.binding.newsTitleTv.text = article?.title
//        holder.binding.newsDateTv.text = article?.publishedAt
//        holder.binding.newsSourceTv.text = article?.author
//        Glide.with(holder.binding.root)
//            .load(article?.urlToImage)
//            .into(holder.binding.newsImage)
    }

    fun submitArticles(newArticle: List<Article?>) {
        articles = newArticle
        notifyDataSetChanged()
    }

    override fun getItemCount() = articles.size

    class NewsViewHolder(val binding: ItemNewsBinding) : ViewHolder(binding.root)
}