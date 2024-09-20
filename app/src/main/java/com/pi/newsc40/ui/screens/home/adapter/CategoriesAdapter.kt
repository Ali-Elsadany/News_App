package com.pi.newsc40.ui.screens.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.pi.newsc40.databinding.ItemCategoryBinding
import com.pi.newsc40.ui.model.Category

class CategoriesAdapter(private var categories: List<Category>, var onItemClick: (Category) -> Unit) :
    Adapter<CategoriesAdapter.CategoriesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
      
        val binding =
            ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoriesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        val category = categories[position]
        holder.binding.root.setOnClickListener {
            onItemClick.invoke(category)
        }
        holder.binding.itemCategoryImage.setImageResource(category.imageId)
        holder.binding.itemCategoryNameTv.text = category.title
        holder.binding.root.setBackgroundColor(ContextCompat.getColor(holder.binding.root.context, category.backgroundColorId))
    }

    override fun getItemCount() = categories.size

    class CategoriesViewHolder(val binding: ItemCategoryBinding) : ViewHolder(binding.root)
}