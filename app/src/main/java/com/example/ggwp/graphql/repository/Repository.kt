package com.example.ggwp.graphql.repository

interface Repository {
    fun getSomething(): String
}

class RepositoryImpl(val remoteRepository: RemoteRepository) : Repository {

    override fun getSomething() =
        "Hello GraphQL"
}