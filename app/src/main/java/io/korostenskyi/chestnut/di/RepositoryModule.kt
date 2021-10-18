package io.korostenskyi.chestnut.di

import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.korostenskyi.chestnut.data.repository.MovieRepositoryImpl
import io.korostenskyi.chestnut.data.repository.SettingsRepositoryImpl
import io.korostenskyi.chestnut.domain.repository.MovieRepository
import io.korostenskyi.chestnut.domain.repository.SettingsRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindMovieRepository(impl: MovieRepositoryImpl): MovieRepository

    companion object {
        @Provides
        @Singleton
        fun provideSettingsRepository(
            @ApplicationContext context: Context
        ): SettingsRepository {
            return SettingsRepositoryImpl(context)
        }
    }
}
