package com.example.movieapp.database.roomdatabase.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
    interface MovieDao {
        @Insert
        suspend fun insert(movie: Room)

        @Query("SELECT * FROM `list of movies` WHERE userId = :userId")
        suspend fun getMoviesForUser(userId: String): List<Room>

        @Query("DELETE FROM `list of movies` WHERE id = :movieId AND userId = :userId")
        suspend fun deleteMovieForUser(movieId: Int, userId: String)
    }
