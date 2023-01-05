package com.example.retrofit_implementation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var adapter: NewsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getNews()
    }

    private fun getNews() {
        val news: Call<Article> = NewService.newsInstance.getHeadlines("in",1)
        news.enqueue(object : Callback<Article>{
            override fun onResponse(call: Call<Article>, response: Response<Article>) {
                val newsd = response.body()
                if(newsd!=null){
                    Log.d("Check",newsd.toString())
                    adapter= NewsAdapter(this@MainActivity,newsd.articles)
                    var a = findViewById<RecyclerView>(R.id.newsList)
                    a.adapter=adapter
                    a.layoutManager=LinearLayoutManager(this@MainActivity)

                }
            }

            override fun onFailure(call: Call<Article>, t: Throwable) {
                Log.d("Check","Error in Fetching News $t")
            }
        })
    }
}