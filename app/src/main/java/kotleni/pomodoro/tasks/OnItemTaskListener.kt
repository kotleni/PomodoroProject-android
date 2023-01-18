package kotleni.pomodoro.tasks

import kotleni.pomodoro.domain.Task

interface OnItemTaskListener {
    fun onItemTaskOpen(task: Task)
    fun onItemTaskRemove(task: Task)
}