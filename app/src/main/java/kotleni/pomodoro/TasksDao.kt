package kotleni.pomodoro

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotleni.pomodoro.domain.Task

@Dao
interface TasksDao {
    @Query("SELECT * FROM task")
    suspend fun getAll(): List<Task>

    @Insert
    suspend fun insertAll(vararg users: Task)

    @Delete
    suspend fun delete(user: Task)
}