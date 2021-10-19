package io.korostenskyi.chestnut.data.local.dao

import androidx.room.*
import io.korostenskyi.chestnut.data.local.TableNames
import io.korostenskyi.chestnut.data.local.model.FavoriteMovieRoom
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteMoviesDao {

    @Query("SELECT * FROM ${TableNames.FAVORITE_MOVIES}")
    fun getAllFavorites(): Flow<List<FavoriteMovieRoom>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addToFavorites(movieRoom: FavoriteMovieRoom)

    @Delete
    suspend fun removeFromFavorites(movieRoom: FavoriteMovieRoom)
}
