package com.okantepe.retrofit.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.okantepe.retrofit.model.NewsModel
import com.okantepe.retrofit.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository): ViewModel() {

    val myResponse: MutableLiveData<Response<NewsModel>> = MutableLiveData()

    fun getPost(){
        viewModelScope.launch {
            val response: Response<NewsModel> = repository.getPost()
            myResponse.value = response
        }
    }

}