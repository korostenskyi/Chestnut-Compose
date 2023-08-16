package io.korostenskyi.chestnut.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.korostenskyi.chestnut.presentation.navigation.NavigationFlow
import io.korostenskyi.chestnut.presentation.utils.IntentUtils
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Provides
    @Singleton
    fun provideNavigationFlow(): NavigationFlow = NavigationFlow()

    @Provides
    @Singleton
    fun provideIntentUtils(
        @ApplicationContext context: Context
    ): IntentUtils = IntentUtils(context)
}
