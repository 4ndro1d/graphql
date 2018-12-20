package com.example.ggwp.graphql.domain

import com.example.ggwp.graphql.common.UseCase
import com.example.ggwp.graphql.domain.model.Restaurant
import io.reactivex.Single

class SearchYelpUseCase(
    private val repository: Repository
) : UseCase<SearchYelpUseCase.Params, Single<List<Restaurant>>> {

    override fun execute(param: Params): Single<List<Restaurant>> =
        repository.searchRestaurants(param.term, param.location)

    data class Params(
        val term: String,
        val location: String
    )
}
