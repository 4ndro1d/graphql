package com.example.ggwp.graphql

import com.apollographql.apollo.ApolloClient
import com.example.ggwp.graphql.presenter.Presenter
import com.example.ggwp.graphql.repository.GraphQLRepository
import com.example.ggwp.graphql.repository.RemoteRepository
import com.example.ggwp.graphql.repository.Repository
import com.example.ggwp.graphql.repository.RepositoryImpl
import okhttp3.OkHttpClient
import org.koin.dsl.module.module
import java.util.concurrent.TimeUnit

val appModule = module {

    single<Repository> { RepositoryImpl(get()) }
    single<RemoteRepository> { GraphQLRepository() }
    single<OkHttpClient> {
        OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    single<ApolloClient> {
        ApolloClient.builder()
            .serverUrl("https://yourserver/graphql/")
            .okHttpClient(get())
            .build()
    }

    factory { Presenter(get()) }

}