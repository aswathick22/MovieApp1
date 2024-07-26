import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.movieapp.database.roomdatabase.data.ListMovieCrossRef
import com.example.movieapp.database.roomdatabase.data.ListWithMovies
import com.example.movieapp.database.roomdatabase.data.Movie
import com.example.movieapp.database.roomdatabase.data.UserList
import com.example.movieapp.remote.data.MovieDetails

@Dao
interface UserListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertList(userList: UserList)

    @Delete
    suspend fun deleteList(userList: UserList)

    @Query("DELETE FROM user_lists WHERE listId = :listId")
    suspend fun deleteListById(listId: Int)

    @Query("DELETE FROM user_lists WHERE userId = :userId")
    suspend fun clearListsForUser(userId: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: MovieDetails)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertListMovieCrossRef(crossRef: ListMovieCrossRef)

    @Transaction
    @Query("SELECT * FROM user_lists WHERE userId = :userId")
    suspend fun getListsForUser(userId: Int): List<ListWithMovies>

    @Transaction
    @Query("SELECT * FROM movies INNER JOIN ListMovieCrossRef ON movies.movieId = ListMovieCrossRef.movieId WHERE ListMovieCrossRef.listId = :listId")
    suspend fun getMoviesForList(listId: Int): List<Movie>
}

