package com.example.movieapp.database.roomdatabase.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "user_lists", indices = [Index(value = ["listName", "userId"], unique = true)])

data class UserList(
    @PrimaryKey(autoGenerate = true) val listId: Int = 0,
    @ColumnInfo(name = "userId") val userId: Int?, // Foreign key from SQLite database
    val listName: String
)


