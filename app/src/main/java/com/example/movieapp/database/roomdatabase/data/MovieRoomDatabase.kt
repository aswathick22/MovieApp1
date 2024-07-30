package com.example.movieapp.database.roomdatabase.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.movieapp.remote.data.MovieItem

@Database(entities = [UserList::class, MovieItem::class, ListMovieCrossRef::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class MovieRoomDatabase : RoomDatabase() {
    abstract fun userListDao(): UserListDao

    companion object {
        @Volatile
        private var INSTANCE: MovieRoomDatabase? = null

        fun getInstance(context: Context): MovieRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MovieRoomDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}



