package com.example.movieapp.database.roomdatabase.data

import androidx.room.Entity

@Entity(primaryKeys = ["listId", "movieId"])

data class ListMovieCrossRef(
    val listId: Int,
    val movieId: Int
)
