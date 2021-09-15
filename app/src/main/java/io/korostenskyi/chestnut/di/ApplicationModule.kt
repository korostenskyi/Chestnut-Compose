package io.korostenskyi.chestnut.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.korostenskyi.chestnut.BuildConfig
import io.korostenskyi.chestnut.domain.model.BuildParams
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Provides
    @Singleton
    fun provideBuildParams(): BuildParams {
        val tmdbBaseUrl = BuildConfig.TMDB_BASE_URL
        val tmdbApiKey = BuildConfig.TMDB_API_KEY
        val isDebug = BuildConfig.DEBUG
        return BuildParams(
            tmdbBaseUrl = tmdbBaseUrl,
            tmdbApiKey = tmdbApiKey,
            isDebug = isDebug
        )
    }
}
