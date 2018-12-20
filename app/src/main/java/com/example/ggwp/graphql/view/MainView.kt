package com.example.ggwp.graphql.view

import com.example.ggwp.graphql.domain.model.Restaurant

interface MainView {

    fun showToast(text: String)

    fun showError(text: String)

    fun showLoading()

    fun showRestaurants(restaurants: List<Restaurant>)
}