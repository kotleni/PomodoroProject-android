package kotleni.pomodoro.repos

import android.content.Context
import androidx.room.Room
import kotleni.pomodoro.AppDatabase
import kotleni.pomodoro.entities.Task

class TasksRepository(context: Context) {
    private val db = Room.databaseBuilder(
        context,
        AppDatabase::class.java, "tasks"
    ).build()

    fun getTasks(): List<Task> {
        return db.getTasksDao().getAll()
    }

    fun addTask(task: Task) {
        db.getTasksDao().insertAll(task)
    }
}