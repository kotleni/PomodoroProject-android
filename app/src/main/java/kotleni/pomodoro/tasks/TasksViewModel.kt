package kotleni.pomodoro.tasks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotleni.pomodoro.domain.Task
import kotleni.pomodoro.domain.repos.TasksRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TasksViewModel(
    private val tasksRepository: TasksRepository
): ViewModel() {
    private val _tasksList: MutableLiveData<List<Task>> = MutableLiveData()
    val tasksList: LiveData<List<Task>>
        get() = _tasksList

    fun loadTasks() = viewModelScope.launch(Dispatchers.Main) {
        val tasks = tasksRepository.getTasks()
        _tasksList.value = tasks
    }
}