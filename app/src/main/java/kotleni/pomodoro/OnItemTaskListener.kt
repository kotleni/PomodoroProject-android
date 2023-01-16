package kotleni.pomodoro

import kotleni.pomodoro.entities.Task

interface OnItemTaskListener {
    fun onItemTaskOpen(task: Task)
    fun onItemTaskRemove(task: Task)
}