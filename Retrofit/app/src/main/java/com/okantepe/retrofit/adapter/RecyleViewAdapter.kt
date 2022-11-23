package com.okantepe.retrofit.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.okantepe.retrofit.R
import com.okantepe.retrofit.model.NewsModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.news_layout.view.*

class RecyclerViewAdapter(private val newsList: NewsModel, private val listener: Listener): RecyclerView.Adapter<RecyclerViewAdapter.RowHolder>() {

    interface Listener{
        fun onItemClick(newsModel: NewsModel)
    }

    lateinit var firebaseAuth: FirebaseAuth

    class RowHolder(itemView: View, firebaseAuth: FirebaseAuth) : RecyclerView.ViewHolder(itemView) {
        fun bind(newsModel: NewsModel, position: Int, listener: Listener){
            itemView.setOnClickListener {
                listener.onItemClick(newsModel)
            }
            itemView.newsName.text = newsModel.articles!![position].title
            Picasso.get().load(newsModel.articles[position].urlToImage).into(itemView.newsImage)

            itemView.favoriteButton.setOnClickListener {

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
        firebaseAuth = FirebaseAuth.getInstance()
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_layout,parent,false)
        return RowHolder(view, firebaseAuth)
    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {
        holder.bind(newsList, position, listener)
    }

    override fun getItemCount(): Int {
        return newsList.articles!!.size
    }
}