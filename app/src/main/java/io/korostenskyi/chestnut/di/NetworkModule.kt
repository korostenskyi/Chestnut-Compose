package io.korostenskyi.chestnut.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.korostenskyi.chestnut.BuildConfig
import io.korostenskyi.chestnut.data.network.MovieNetworkDataSource
import io.korostenskyi.chestnut.data.network.api.TmdbApi
import io.korostenskyi.chestnut.data.network.impl.MovieNetworkDataSourceImpl
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
    fun bindMovieNetworkDataSource(impl: MovieNetworkDataSourceImpl): MovieNetworkDataSource

    companion object {

        @Provides
        @Singleton
        @Named(DiNames.LOGGING_INTERCEPTOR)
        fun provideLoggingInterceptor(): Interceptor {
            val loggingLevel = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
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
        fun provideJsonConfig(): Json {
            return Json {
                ignoreUnknownKeys = true
            }
        }

        @Provides
        @Singleton
        fun provideTmdbApi(
            okHttpClient: OkHttpClient,
            jsonConfig: Json
        ): TmdbApi {
            val contentType = "application/json".toMediaType()
            return Retrofit.Builder()
                .baseUrl(BuildConfig.TMDB_BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(jsonConfig.asConverterFactory(contentType))
                .build()
                .create()
        }
    }
}
