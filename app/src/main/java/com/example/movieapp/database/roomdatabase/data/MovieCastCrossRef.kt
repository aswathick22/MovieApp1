package com.example.movieapp.database.roomdatabase.data

import androidx.room.Entity

@Entity(primaryKeys = ["movieId", "personId"])
data class MovieCastCrossRef(
    val movieId: Int,
    val personId: Int
)