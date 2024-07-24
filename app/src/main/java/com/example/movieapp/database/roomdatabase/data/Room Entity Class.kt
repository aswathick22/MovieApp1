package com.example.movieapp.database.roomdatabase.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "list of movies")

data class Room(
    @PrimaryKey val id: Int,
    val title: String,
    val overview: String,
    val posterPath: String,
    val userId: String  // Unique identifier for each user
)


