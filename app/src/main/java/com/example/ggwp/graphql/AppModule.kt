package com.example.ggwp.graphql

import com.apollographql.apollo.ApolloClient
import com.example.ggwp.graphql.presenter.Presenter
import com.example.ggwp.graphql.repository.GraphQLRepository
import com.example.ggwp.graphql.repository.RemoteRepository
import com.example.ggwp.graphql.repository.Repository
import com.example.ggwp.graphql.repository.RepositoryImpl
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

    single<Repository> { RepositoryImpl(get()) }
    single<RemoteRepository> { GraphQLRepository(get()) }

    single<OkHttpClient> {
        OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addNetworkInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS))
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
