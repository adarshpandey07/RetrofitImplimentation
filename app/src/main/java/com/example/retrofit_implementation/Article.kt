package com.example.retrofit_implementation

data class Article(
    val articles: List<ArticleX>,
    val status: String,
    val totalResults: Int
)