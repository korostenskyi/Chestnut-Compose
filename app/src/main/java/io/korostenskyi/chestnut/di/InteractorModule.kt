package io.korostenskyi.chestnut.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.korostenskyi.chestnut.domain.interactor.MovieInteractor
import io.korostenskyi.chestnut.domain.interactor.SettingsInteractor
import io.korostenskyi.chestnut.domain.interactor.impl.MovieInteractorImpl
import io.korostenskyi.chestnut.domain.interactor.impl.SettingsInteractorImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface InteractorModule {

    @Binds
    @Singleton
    fun bindMovieInteractor(impl: MovieInteractorImpl): MovieInteractor

    @Binds
    @Singleton
    fun bindSettingsInteractor(impl: SettingsInteractorImpl): SettingsInteractor
}
