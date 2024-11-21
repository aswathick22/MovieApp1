package com.example.movieapp.database.roomdatabase.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.movieapp.remote.data.MovieItem

@Dao
interface UserListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertList(userList: UserList)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: MovieItem)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertListMovieCrossRef(crossRef: ListMovieCrossRef)

    @Transaction
    @Query("SELECT * FROM user_lists WHERE userId = :userId")
    suspend fun getListsForUser(userId: Int?): List<UserList>

    @Transaction
    @Query("SELECT * FROM `list of movies` INNER JOIN ListMovieCrossRef ON `list of movies`.id = ListMovieCrossRef.movieId WHERE ListMovieCrossRef.listId = :listId")
    suspend fun getMoviesForList(listId: Int): List<MovieItem>

    @Query("DELETE FROM user_lists WHERE listId = :listId")
    suspend fun deleteList(listId: Int)

    @Query("DELETE FROM user_lists WHERE userId = :userId")
    suspend fun clearAllLists(userId: Int)

}




