package io.korostenskyi.chestnut.domain.interactor

import io.korostenskyi.chestnut.domain.model.Movie

interface MovieInteractor {

    suspend fun retrievePopularMovies(page: Int): List<Movie>
}
