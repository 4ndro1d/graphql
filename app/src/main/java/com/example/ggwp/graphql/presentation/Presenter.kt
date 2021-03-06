package com.example.ggwp.graphql.presentation

import com.example.ggwp.graphql.common.BasePresenter
import com.example.ggwp.graphql.domain.SearchYelpUseCase
import com.example.ggwp.graphql.view.MainView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class Presenter(private val useCase: SearchYelpUseCase) : BasePresenter() {

    lateinit var view: MainView

    fun attach(mainView: MainView) {
        this.view = mainView
    }

    override fun start() {
        //stub
    }

    fun searchRestaurants() {
        disposables += useCase.execute(SearchYelpUseCase.Params("burrito", "san francisco"))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = view::showRestaurants,
                onError = Timber::e
            )
    }

    fun detach() {
        disposables.clear()
    }
}