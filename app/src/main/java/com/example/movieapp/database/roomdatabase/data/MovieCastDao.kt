package com.example.movieapp.database.roomdatabase.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.movieapp.remote.data.MovieItem

@Dao
interface MovieCastDao {

    @Transaction
    @Query("SELECT * FROM `list of movies` INNER JOIN MovieCastCrossRef ON `list of movies`.id = MovieCastCrossRef.movieId WHERE MovieCastCrossRef.personId = :personId")
    fun getMoviesForCast(personId: Int): LiveData<List<MovieItem>>

}