package com.route.newsapp;

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import androidx.recyclerview.widget.RecyclerView;
import com.route.newsapp.api.model.ArticlesItem
import com.route.newsapp.databinding.LayoutNewsBinding

public class NewsAdapter(var newsList:List<ArticlesItem?>?):
    RecyclerView.Adapter<NewsAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val view = LayoutInflater.from(parent.context)
//            .inflate(R.layout.layout_news,parent,false);
//        return ViewHolder(view)
        val newsItemBinding:LayoutNewsBinding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.layout_news,parent,false)
        return ViewHolder(newsItemBinding)

    }

    override fun getItemCount(): Int {
        return newsList?.size?:0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val newsItem = newsList?.get(position)
        holder.bind(newsItem!!)


        //holder.itemBinding.root.setOnClickListener {  } //; holder.itemView.setOnClickListener {  }
        //holder.itemBinding.image.setOnClickListener{}
    }

    fun changeData(newsList: List<ArticlesItem?>?){
        this.newsList=newsList;
        notifyDataSetChanged()
    }

    class ViewHolder(val itemBinding: LayoutNewsBinding)
        :RecyclerView.ViewHolder(itemBinding.root){

        fun bind(newsItem:ArticlesItem){
            itemBinding.newsItem=newsItem
           // itemBinding.invalidateAll()// refresh item
        }
    }
}
