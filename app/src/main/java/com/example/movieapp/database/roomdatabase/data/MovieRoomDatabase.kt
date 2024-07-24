/*
package com.example.movieapp.database.roomdatabase.data

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [Room::class], version = 1, exportSchema = false)
abstract class MovieRoomDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object {
        @Volatile
        private var INSTANCE: MovieRoomDatabase? = null

        fun getInstance(context: Context): MovieRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MovieRoomDatabase::class.java,
                    "movie_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}

}*/
