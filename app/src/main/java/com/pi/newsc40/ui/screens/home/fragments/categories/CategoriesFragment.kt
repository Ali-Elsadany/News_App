package com.pi.newsc40.ui.screens.home.fragments.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.pi.newsc40.R
import com.pi.newsc40.databinding.FragmentCategoriesBinding
import com.pi.newsc40.ui.base.BaseFragment
import com.pi.newsc40.ui.model.Category
import com.pi.newsc40.ui.screens.home.HomeActivity
import com.pi.newsc40.ui.screens.home.adapter.CategoriesAdapter
import com.pi.newsc40.ui.screens.home.fragments.news.NewsFragment

class CategoriesFragment() : BaseFragment<FragmentCategoriesBinding>() {
    var categoriesAdapter = CategoriesAdapter(Category.categories){
        (activity as HomeActivity).showFragment(NewsFragment(it))
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_categories,
            container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }
    fun initRecyclerView(){
        binding!!.categoriesRecyclerView.adapter = categoriesAdapter
    }

}