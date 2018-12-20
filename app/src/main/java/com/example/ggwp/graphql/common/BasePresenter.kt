package com.example.ggwp.graphql.common

import io.reactivex.disposables.CompositeDisposable

abstract class BasePresenter {

    protected val disposables = CompositeDisposable()

    /**
     * mainView calles this when fully initialized (e.g. onViewCreated)
     */
    abstract fun start()

    /**
     * mainView calles this before it is destroyed (e.g. onViewDestroyed)
     */
    open fun stop() {
        disposables.clear()
    }
}