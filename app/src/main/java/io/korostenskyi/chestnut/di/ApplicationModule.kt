package io.korostenskyi.chestnut.di

import android.content.Context
import coil.ImageLoader
import coil.util.DebugLogger
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.korostenskyi.chestnut.BuildConfig
import io.korostenskyi.chestnut.domain.model.BuildParams
import io.korostenskyi.chestnut.presentation.navigation.NavigationFlow
import io.korostenskyi.chestnut.presentation.utils.IntentUtils
import okhttp3.OkHttpClient
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
        val isSentryEnabled = BuildConfig.ENABLE_SENTRY
        val sentryUrl = BuildConfig.SENTRY_DNS_URL
        return BuildParams(
            tmdbBaseUrl = tmdbBaseUrl,
            tmdbApiKey = tmdbApiKey,
            isDebug = isDebug,
            isSentryEnabled = isSentryEnabled,
            sentryUrl = sentryUrl
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

    @Provides
    @Singleton
    fun provideImageLoader(
        @ApplicationContext context: Context,
        buildParams: BuildParams,
        okHttpClient: OkHttpClient
    ): ImageLoader {
        return ImageLoader.Builder(context).apply {
            if (buildParams.isDebug) {
                logger(DebugLogger())
            }
            okHttpClient(okHttpClient)
        }
        .build()
    }
}
