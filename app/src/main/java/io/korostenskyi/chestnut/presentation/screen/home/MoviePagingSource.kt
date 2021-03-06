package io.korostenskyi.chestnut.presentation.screen.home

import androidx.paging.PagingSource
import androidx.paging.PagingState
import io.korostenskyi.chestnut.domain.interactor.MovieInteractor
import io.korostenskyi.chestnut.domain.model.Movie
import kotlinx.coroutines.flow.MutableStateFlow

class MoviePagingSource(
    private val movieInteractor: MovieInteractor,
    private val isLoadingFlow: MutableStateFlow<Boolean>
) : PagingSource<Int, Movie>() {

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val nextPage = params.key ?: 1
            isLoadingFlow.emit(true)
            val page = movieInteractor.retrievePopularMovies(nextPage)
            isLoadingFlow.emit(false)
            LoadResult.Page(
                data = page.movies,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (nextPage == page.totalPages) null else nextPage + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
