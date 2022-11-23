package com.okantepe.retrofit.repository

import com.okantepe.retrofit.model.NewsModel
import com.okantepe.retrofit.service.RetrofitInstance
import retrofit2.Response

class Repository {

    suspend fun getPost(): Response<NewsModel>{
        return RetrofitInstance.api.getPost()
    }

}