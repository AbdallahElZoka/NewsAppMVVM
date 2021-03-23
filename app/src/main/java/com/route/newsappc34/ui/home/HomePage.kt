package com.route.newsappc34.ui.home

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.route.islamigsun.base.BaseActivity
import com.route.newsappc34.Constants
import com.route.newsappc34.NewsAdapter
import com.route.newsappc34.R
import com.route.newsappc34.api.ApiManager
import com.route.newsappc34.api.model.ArticlesItem
import com.route.newsappc34.api.model.NewsResponse
import com.route.newsappc34.api.model.SourcesItem
import com.route.newsappc34.api.model.SourcesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomePage : BaseActivity(),TabLayout.OnTabSelectedListener {
    //5909ae28122a471d8b0c237d5989cb73
    lateinit var progressBar: ProgressBar
    lateinit var tabLayout: TabLayout
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: NewsAdapter
    lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)
        // how to declare a view Model
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
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
                progressBar.visibility = View.VISIBLE
            else
                progressBar.visibility = View.GONE

        })
        viewModel.sourcesLivesData.observe(this, Observer {sourcesList->
            showSourcesInTabLayout(sourcesList)
        })
        viewModel.newsLivedata.observe(this, Observer {newsList->
            adapter.changeData(newsList)
        })
    }

    private fun setUpViews() {// view
        progressBar =findViewById(R.id.progress)
        tabLayout =findViewById(R.id.tabLayout)
        recyclerView =findViewById(R.id.recycler_view)
        adapter = NewsAdapter(null)
        recyclerView.adapter=adapter
    }


    private fun showSourcesInTabLayout(sources: List<SourcesItem?>?) {

        sources?.forEach {item->
            val tab = tabLayout.newTab()
            tab.setText(item?.name)
            tab.setTag(item)
            tabLayout.addTab(tab)
        }
        tabLayout.addOnTabSelectedListener(this)
        tabLayout.getTabAt(0)?.select()
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