package io.korostenskyi.chestnut.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.korostenskyi.chestnut.data.network.MovieNetworkDataSource
import io.korostenskyi.chestnut.data.network.api.TmdbApi
import io.korostenskyi.chestnut.data.network.impl.MovieNetworkDataSourceImpl
import io.korostenskyi.chestnut.domain.model.BuildParams
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface NetworkModule {

    @Binds
    @Singleton
    fun bindTmdbNetworkDataSource(impl: MovieNetworkDataSourceImpl): MovieNetworkDataSource

    companion object {

        @Provides
        @Singleton
        @Named(DiNames.LOGGING_INTERCEPTOR)
        fun provideLoggingInterceptor(
            params: BuildParams
        ): Interceptor {
            val loggingLevel = if (params.isDebug) HttpLoggingInterceptor.Level.BODY
            else HttpLoggingInterceptor.Level.NONE
            return HttpLoggingInterceptor().apply {
                level = loggingLevel
            }
        }

        @Provides
        @Singleton
        fun provideOkHttpClient(
            @Named(DiNames.LOGGING_INTERCEPTOR) loggingInterceptor: Interceptor
        ): OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()
        }

        @Provides
        @Singleton
        fun provideJsonConfig() = Json {
            ignoreUnknownKeys = true
        }

        @Provides
        @Singleton
        fun provideTmdbApi(
            params: BuildParams,
            okHttpClient: OkHttpClient,
            jsonConfig: Json
        ): TmdbApi {
            val contentType = "application/json".toMediaType()
            return Retrofit.Builder()
                .baseUrl(params.tmdbBaseUrl)
                .client(okHttpClient)
                .addConverterFactory(jsonConfig.asConverterFactory(contentType))
                .build()
                .create()
        }
    }
}
