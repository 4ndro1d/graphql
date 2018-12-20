package com.example.ggwp.graphql.remote

import SearchYelpQuery
import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.rx2.Rx2Apollo
import io.reactivex.Observable

interface RemoteRepository {
    fun getData(term: String, location: String): Observable<Response<SearchYelpQuery.Data>>
}

class GraphQLRepository(private val apolloClient: ApolloClient) : RemoteRepository {

    override fun getData(
        term: String,
        location: String)
        : Observable<Response<SearchYelpQuery.Data>> {

        val query = SearchYelpQuery.builder()
            .term(term)
            .location(location)
            .build()

        val apolloCall: ApolloCall<SearchYelpQuery.Data> = apolloClient.query(query)

        return Rx2Apollo.from(apolloCall)
    }
}
