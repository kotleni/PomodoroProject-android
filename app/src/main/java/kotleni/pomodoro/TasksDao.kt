package kotleni.pomodoro

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotleni.pomodoro.entities.Task

@Dao
interface TasksDao {
    @Query("SELECT * FROM task")
    fun getAll(): List<Task>

    @Insert
    fun insertAll(vararg users: Task)

    @Delete
    fun delete(user: Task)
}