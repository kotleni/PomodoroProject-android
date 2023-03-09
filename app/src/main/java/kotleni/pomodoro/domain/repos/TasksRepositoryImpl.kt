package kotleni.pomodoro.domain.repos

import android.content.Context
import androidx.room.Room
import kotleni.pomodoro.AppDatabase
import kotleni.pomodoro.domain.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TasksRepositoryImpl(context: Context) : TasksRepository {
    private val db = Room.databaseBuilder(
        context,
        AppDatabase::class.java, AppDatabase.TASKS_DB_NAME
    ).build()

    override suspend fun getTasks(): List<Task> {
        return withContext(Dispatchers.IO) {
            db.getTasksDao().getAll()
        }
    }

    override suspend fun addTask(task: Task) {
        withContext(Dispatchers.IO) {
            db.getTasksDao().insertAll(task)
        }
    }

    override suspend fun removeTask(task: Task) {
        withContext(Dispatchers.IO) {
            db.getTasksDao().delete(task)
        }
    }
}