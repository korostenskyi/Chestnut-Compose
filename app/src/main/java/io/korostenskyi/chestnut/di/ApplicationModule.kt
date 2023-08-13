package io.korostenskyi.chestnut.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.korostenskyi.chestnut.BuildConfig
import io.korostenskyi.chestnut.domain.model.BuildParams
import io.korostenskyi.chestnut.presentation.navigation.NavigationFlow
import io.korostenskyi.chestnut.presentation.utils.IntentUtils
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
            isDebug = isDebug,
        )
    }

    @Provides
    @Singleton
    fun provideNavigationFlow(): NavigationFlow = NavigationFlow()

    @Provides
    @Singleton
    fun provideIntentUtils(
        @ApplicationContext context: Context
    ): IntentUtils = IntentUtils(context)
}
