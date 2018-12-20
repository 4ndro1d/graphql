package com.example.ggwp.graphql.presenter

import com.example.ggwp.graphql.repository.Repository
import com.example.ggwp.graphql.view.View
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class Presenter(private val repository: Repository) {

    lateinit var view: View

    private val compositeDisposable = CompositeDisposable()

    fun attach(view: View) {
        this.view = view
    }

    fun doSomething() {
        compositeDisposable += repository.getSomething()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = {
                    if (!it.hasErrors()) {
                        view.showText("Found ${it.data()?.search()?.total()} results")
                    }
                },
                onError = Timber::e
            )
    }

    fun detach() {
        compositeDisposable.clear()
    }
}