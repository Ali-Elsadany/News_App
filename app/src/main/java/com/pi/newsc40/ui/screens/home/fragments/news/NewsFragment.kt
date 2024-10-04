package com.pi.newsc40.ui.screens.home.fragments.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayout
import com.pi.newsc40.R
import com.pi.newsc40.data.api.model.SourceDM
import com.pi.newsc40.databinding.FragmentNewsBinding
import com.pi.newsc40.domain.model.Source
import com.pi.newsc40.ui.base.BaseFragment
import com.pi.newsc40.ui.model.Category
import com.pi.newsc40.ui.screens.home.adapter.NewsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFragment(private var category: Category) : BaseFragment<FragmentNewsBinding>() {
    lateinit var adapter: NewsAdapter
    private val viewModel: NewsViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        viewModel = ViewModelProvider(this)[NewsViewModel::class.java]
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_news, container, false)
        binding!!.lifecycleOwner = viewLifecycleOwner // Important for LiveData
        binding!!.vm = viewModel
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        initTabClickListener()
        setupObservers()
        viewModel.getSources(category.id)
    }

    private fun setupObservers() {
        viewModel.isLoadingLiveData.observe(viewLifecycleOwner) {
//            if (it) showLoading()
//            else hideLoading()
        }
        viewModel.errorLiveData.observe(viewLifecycleOwner) {
            if (it.isNullOrEmpty()) hideError()
            else showError(it)
        }

        viewModel.sourcesLiveData.observe(viewLifecycleOwner) {
            if (it.isNullOrEmpty()) return@observe
            showTabs(it)
        }

        viewModel.articlesLiveData.observe(viewLifecycleOwner) {
            adapter.submitArticles(it)
        }
    }

    private fun initRecyclerView() {
        adapter = NewsAdapter(emptyList())
        binding!!.newsRecyclerView.adapter = adapter
    }

    private fun initTabClickListener() {
        binding!!.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewModel.getArticles(tab!!.tag as String)
            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {
                viewModel.getArticles(tab!!.tag as String)
            }

        })
    }

    private fun showTabs(sources: List<Source>) {
        for (source in sources) {
            val tab = binding!!.tabLayout.newTab()
            tab.text = source.name
            tab.tag = source.id
            binding!!.tabLayout.addTab(tab)
        }
    }

    private fun showError(errorMessage: String, onRetryClick: (() -> Unit)? = null) {
        binding!!.errorView.root.visibility = View.VISIBLE
        binding!!.errorView.errorText.text = errorMessage
        binding!!.errorView.retryButton.setOnClickListener {
            onRetryClick?.invoke()
        }
    }

    private fun hideError() {
        binding!!.errorView.root.visibility = View.GONE
    }
}
