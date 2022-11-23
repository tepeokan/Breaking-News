package com.okantepe.retrofit.service

import com.okantepe.retrofit.model.NewsModel
import retrofit2.Response
import retrofit2.http.GET

interface SimpleAPI {
    @GET("top-headlines?country=us&apiKey=d9a165dcde1346a4a83df27219aea06e")
    suspend fun getPost(): Response<NewsModel>
}