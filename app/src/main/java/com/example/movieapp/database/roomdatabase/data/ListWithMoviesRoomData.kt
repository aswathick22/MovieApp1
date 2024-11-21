package com.example.movieapp.database.roomdatabase.data

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.movieapp.remote.data.MovieItem

data class ListWithMovies(
    @Embedded val userList: UserList,
    @Relation(
        parentColumn = "listId",
        entityColumn = "movieId",
        associateBy = Junction(ListMovieCrossRef::class)
    )
    val movies: List<MovieItem>
)
