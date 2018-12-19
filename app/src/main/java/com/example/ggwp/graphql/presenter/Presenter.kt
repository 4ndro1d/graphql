package com.example.ggwp.graphql.presenter

import com.example.ggwp.graphql.view.View
import com.example.ggwp.graphql.repository.Repository

class Presenter(private val repository: Repository) {

    lateinit var view: View

    fun attach(view: View) {
        this.view = view
    }

    fun doSomething() {
        view.showText(repository.getSomething())
    }

    fun buttonClicked() {
        view.showToast(repository.getSomething())
    }

}