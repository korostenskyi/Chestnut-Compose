package io.korostenskyi.chestnut.domain.repository

import io.korostenskyi.chestnut.domain.model.Movie

interface MovieRepository {

    suspend fun retrievePopularMovies(page: Int): List<Movie>
}
