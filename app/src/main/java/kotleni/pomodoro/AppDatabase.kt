package kotleni.pomodoro

import androidx.room.Database
import androidx.room.RoomDatabase
import kotleni.pomodoro.entities.Task

@Database(entities = [Task::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getTasksDao(): TasksDao
}