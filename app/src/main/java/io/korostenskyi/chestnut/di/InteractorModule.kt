package io.korostenskyi.chestnut.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.korostenskyi.chestnut.domain.interactor.MovieInteractor
import io.korostenskyi.chestnut.domain.interactor.impl.MovieInteractorImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface InteractorModule {

    @Binds
    @Singleton
    fun bindMovieInteractor(impl: MovieInteractorImpl): MovieInteractor
}
