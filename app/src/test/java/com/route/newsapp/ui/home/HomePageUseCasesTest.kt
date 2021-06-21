package com.route.newsapp.ui.home

import com.route.newsapp.api.model.ArticlesItem
import org.junit.Assert.*
import org.junit.Test

class HomePageUseCasesTest{

    @Test
    fun `filterNewsByAuthor when empty list then empty list`(){
        // arrange
        val data = mutableListOf<ArticlesItem>()
        val author = "nabil";
        // act
        val res = filterNewsByAuthor(data,author);
        //assert
        assertEquals(0,res.size)
    }
    @Test
    fun `filterNewsByAuthor when author sent then filterd news by auhtor`(){
        // arrange
        val data = mutableListOf<ArticlesItem>(ArticlesItem(author = "nabil"),
        ArticlesItem(author = "ali"))
        val author = "nabil";
        // act
        val res = filterNewsByAuthor(data,author);
        //assert
        res.forEach{news->
            assertEquals(author,news.author);

        }
    }


}