package com.route.newsapp.ui.home

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.google.android.material.tabs.TabLayout
import com.route.islamigsun.base.BaseActivity
import com.route.newsapp.NewsAdapter
import com.route.newsapp.R
import com.route.newsapp.api.model.ArticlesItem
import com.route.newsapp.api.model.SourcesItem
import com.route.newsapp.databinding.ActivityHomePageBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomePage : BaseActivity<ActivityHomePageBinding,HomeViewModel>(),TabLayout.OnTabSelectedListener {
    //5909ae28122a471d8b0c237d5989cb73

    lateinit var adapter: NewsAdapter

    override fun getLayoutId(): Int {
        return R.layout.activity_home_page
    }

    private val homeViewModel: HomeViewModel by viewModels()

    override fun initializeViewModel(): HomeViewModel {
        return homeViewModel;
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpViews()
        viewModel.getSources()
        observeToLiveData()
    }

    fun observeToLiveData(){
        viewModel.messageLiveData.observe(this, Observer {message->
            showDialoge(message =message ,
                posActionName = getString(R.string.ok),
                posAction = DialogInterface.OnClickListener { dialog, which ->
                    dialog.dismiss()
                })
        })
        viewModel.showProgressLiveData.observe(this, Observer {show->
            if(show)
                viewDataBinding.progress.visibility = View.VISIBLE
            else
                viewDataBinding.progress.visibility = View.GONE

        })
        viewModel.sourcesLivesData.observe(this, Observer {sourcesList->
            showSourcesInTabLayout(sourcesList)
        })
        viewModel.newsLivedata.observe(this, Observer {newsList->
            adapter.changeData(newsList)
        })
    }

    private fun setUpViews() {// view
          adapter = NewsAdapter(null)
         viewDataBinding.recyclerView.adapter=adapter
    }


    private fun showSourcesInTabLayout(sources: List<SourcesItem?>?) {

        sources?.forEach {item->
            val tab = viewDataBinding.tabLayout.newTab()
            tab.setText(item?.name)
            tab.setTag(item)
            viewDataBinding.tabLayout.addTab(tab)
        }
        viewDataBinding.tabLayout.addOnTabSelectedListener(this)
        viewDataBinding.tabLayout.getTabAt(0)?.select()
    }
    override fun onTabReselected(tab: TabLayout.Tab?) {
        val item = tab?.tag as SourcesItem
        viewModel.getNews(item.id)
    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        val item = tab?.tag as SourcesItem
        viewModel.getNews(item.id)
    }


    override fun onTabUnselected(tab: TabLayout.Tab?) {
    }

    private fun showNewsInRecyclerView(newsList: List<ArticlesItem?>?) {
        adapter.changeData(newsList)
    }

}