package com.example.ggwp.graphql.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ggwp.graphql.R
import com.example.ggwp.graphql.domain.model.Restaurant

class RestaurantsAdapter : RecyclerView.Adapter<ViewHolder>() {

    var restaurants: List<Restaurant> = listOf(Restaurant("Pull to load", ""))

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_restautrant, parent, false))

    override fun getItemCount(): Int =
        restaurants.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(restaurants[position])
    }
}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val nameText by lazy { view.findViewById<TextView>(R.id.nameText) }
    private val imageView by lazy { view.findViewById<ImageView>(R.id.imageView) }

    fun bind(restaurant: Restaurant) {
        nameText.text = restaurant.name
        Glide.with(imageView.context).load(restaurant.photoUrl).into(imageView)
    }
}