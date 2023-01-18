package kotleni.pomodoro.domain.repos

import android.content.Context
import androidx.room.Room
import kotleni.pomodoro.AppDatabase
import kotleni.pomodoro.domain.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TasksRepository(context: Context) {
    private val db = Room.databaseBuilder(
        context,
        AppDatabase::class.java, AppDatabase.TASKS_DB_NAME
    ).build()

    suspend fun getTasks(): List<Task>
        = withContext(Dispatchers.IO) {
        db.getTasksDao().getAll()
    }

    suspend fun addTask(task: Task)
        = withContext(Dispatchers.IO) {
        db.getTasksDao().insertAll(task)
    }
}