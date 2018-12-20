package com.example.ggwp.graphql.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ggwp.graphql.R
import com.example.ggwp.graphql.domain.model.Restaurant
import com.example.ggwp.graphql.presentation.Presenter
import com.example.ggwp.graphql.toast
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity(), MainView {

    private val presenter: Presenter by inject()

    private val restaurantsAdapter = RestaurantsAdapter()
    private val linearLayoutManager = LinearLayoutManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler.apply {
            hasFixedSize()
            layoutManager = linearLayoutManager
            adapter = restaurantsAdapter
        }

        presenter.attach(this)

        swipeContainer.setOnRefreshListener {
            presenter.searchRestaurants()
        }
    }

    override fun showToast(text: String) {
        toast(text)
    }

    override fun showError(text: String) {
        toast(text)
    }

    override fun showLoading() {
        swipeContainer.isRefreshing = true
    }

    override fun showRestaurants(restaurants: List<Restaurant>) {
        swipeContainer.isRefreshing = false
        restaurantsAdapter.restaurants = restaurants
        restaurantsAdapter.notifyDataSetChanged()
    }

    override fun onDestroy() {
        presenter.detach()
        super.onDestroy()
    }
}
