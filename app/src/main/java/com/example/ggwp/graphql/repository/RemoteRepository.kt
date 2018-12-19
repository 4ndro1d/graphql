package com.example.ggwp.graphql.repository

import com.apollographql.apollo.ApolloClient

interface RemoteRepository {
    fun getData()
}

class GraphQLRepository(private val apolloClient: ApolloClient) : RemoteRepository {
    override fun getData() {

    }
}

class RetrofitRepository() : RemoteRepository {
    override fun getData() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
