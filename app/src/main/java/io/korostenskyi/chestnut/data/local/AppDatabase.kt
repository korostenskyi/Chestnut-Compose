package io.korostenskyi.chestnut.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import io.korostenskyi.chestnut.data.local.dao.FavoriteMoviesDao
import io.korostenskyi.chestnut.data.local.model.FavoriteMovieRoom

@Database(
    entities = [FavoriteMovieRoom::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun favoriteMoviesDao(): FavoriteMoviesDao

    companion object {

        private const val DATABASE_NAME = "chestnut_db"

        private var instance: AppDatabase? = null

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .build()
        }

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }
    }
}
