package io.korostenskyi.chestnut.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import io.korostenskyi.chestnut.data.local.TableNames

@Entity(tableName = TableNames.FAVORITE_MOVIES)
data class FavoriteMovieRoom(
    @PrimaryKey
    @ColumnInfo(name = "movie_id")
    val movieId: Int
)
