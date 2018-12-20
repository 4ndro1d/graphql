package com.example.ggwp.graphql.domain

import SearchYelpQuery
import com.apollographql.apollo.api.Response
import com.example.ggwp.graphql.common.Mapper
import com.example.ggwp.graphql.domain.model.Restaurant

class RemoteRestaurantsMapper : Mapper<Response<SearchYelpQuery.Data>, List<Restaurant>> {

    override fun map(from: Response<SearchYelpQuery.Data>): List<Restaurant> = with(from) {
        this.data()?.search()?.business()?.map { Restaurant(it.name()!!) }
    }!!
}

