package com.example.ggwp.graphql

import com.apollographql.apollo.ApolloClient
import com.example.ggwp.graphql.domain.RemoteRestaurantsMapper
import com.example.ggwp.graphql.domain.Repository
import com.example.ggwp.graphql.domain.RepositoryImpl
import com.example.ggwp.graphql.domain.SearchYelpUseCase
import com.example.ggwp.graphql.presentation.Presenter
import com.example.ggwp.graphql.remote.GraphQLRepository
import com.example.ggwp.graphql.remote.RemoteRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.module
import java.util.concurrent.TimeUnit

class AppModule {
    companion object {
        internal const val API_KEY =
            "0-SRYXj6rzRyj7CBTblqMio4bJMXIF4E8rc8976k-DID_UbqusVaEuu0R97jGb5CUZ0Mte2ZEVm1_tCwTtOmXDxPg5ef-3vPwIVDFAB-4r5X9rVxxQLD2hPpcmAbXHYx"
        internal const val BASE_URL = "https://api.yelp.com/v3/graphql"
    }
}

val appModule = module {

    single<Repository> { RepositoryImpl(get(), get()) }

    single { RemoteRestaurantsMapper() }
    single<RemoteRepository> { GraphQLRepository(get()) }

    single<SearchYelpUseCase> { SearchYelpUseCase(get()) }

    single<OkHttpClient> {
        OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addNetworkInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
            .addInterceptor { chain ->
                val original = chain.request()
                val requestBuilder = original.newBuilder()
                    .header("Authorization",
                        "Bearer ${AppModule.API_KEY}")
                val request = requestBuilder.build()
                chain.proceed(request)
            }
            .build()
    }

    single<ApolloClient> {
        ApolloClient.builder()
            .serverUrl(AppModule.BASE_URL)
            .okHttpClient(get())
            .build()
    }

    factory { Presenter(get()) }
}
