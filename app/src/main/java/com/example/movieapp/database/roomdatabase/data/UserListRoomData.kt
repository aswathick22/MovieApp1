package com.example.movieapp.database.roomdatabase.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_lists")

data class UserList(
    @PrimaryKey(autoGenerate = true) val listId: Int = 0,
    @ColumnInfo(name = "userId") val userId: String, // Foreign key from SQLite database
    val listName: String
)


