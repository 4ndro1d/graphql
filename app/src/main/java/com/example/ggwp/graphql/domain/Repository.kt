package com.example.ggwp.graphql.domain

import com.example.ggwp.graphql.domain.model.Restaurant
import com.example.ggwp.graphql.remote.RemoteRepository
import io.reactivex.Single

interface Repository {
    fun searchRestaurants(term: String, location: String): Single<List<Restaurant>>
}

class RepositoryImpl(
    private val remoteRepository: RemoteRepository,
    private val remoteRestaurantsMapper: RemoteRestaurantsMapper) :
    Repository {

    override fun searchRestaurants(term: String, location: String): Single<List<Restaurant>> =
        remoteRepository.getData(term, location)
            .map {
                remoteRestaurantsMapper.map(it)
            }
            .singleOrError()
}