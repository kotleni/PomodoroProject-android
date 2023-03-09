package kotleni.pomodoro.domain.repos

import kotleni.pomodoro.domain.Task
interface TasksRepository {
    suspend fun getTasks(): List<Task>

    suspend fun addTask(task: Task)

    suspend fun removeTask(task: Task)
}