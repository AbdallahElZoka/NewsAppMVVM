package com.route.newsappc34.ui.home;

import com.route.newsappc34.api.model.ArticlesItem

// pure function
fun filterNewsByAuthor(newsList: List<ArticlesItem?>, author:String):
        MutableList<ArticlesItem> {
    val filteredList = mutableListOf<ArticlesItem>()
    newsList.forEach {
        if(it?.author?.equals(author) == true){
            filteredList.add(it);
        }
    }
    return filteredList;
}