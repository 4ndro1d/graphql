package com.example.ggwp.graphql.repository

import SearchYelpQuery
import com.apollographql.apollo.api.Response
import io.reactivex.Observable

interface Repository {
    fun getSomething(): Observable<Response<SearchYelpQuery.Data>>
}

class RepositoryImpl(private val remoteRepository: RemoteRepository) : Repository {

    override fun getSomething() = remoteRepository.getData()
}