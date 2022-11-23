package com.okantepe.retrofit.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.okantepe.retrofit.R
import com.okantepe.retrofit.adapter.RecyclerViewAdapter
import com.okantepe.retrofit.model.NewsModel
import com.okantepe.retrofit.repository.Repository
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity(), RecyclerViewAdapter.Listener {

    private lateinit var viewModel: MainViewModel
    private var adapter: RecyclerViewAdapter? = null
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        recyclerViewNews.layoutManager = layoutManager

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this,viewModelFactory)[MainViewModel::class.java]
        viewModel.getPost()
        viewModel.myResponse.observe(this, Observer { response ->
            if (response.isSuccessful) {
                adapter = RecyclerViewAdapter(response.body()!!, this@MainActivity)
                recyclerViewNews.adapter = adapter
                Log.d("Response", response.body()?.articles!![0].title!!)
            }else{
                Log.d("Response1", response.errorBody().toString())
            }
        })

        firebaseAuth = FirebaseAuth.getInstance()


    }

    override fun onItemClick(newsModel: NewsModel) {
        Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show()
    }
}