package kotleni.pomodoro.newtask

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotleni.pomodoro.domain.Task
import kotleni.pomodoro.domain.repos.TasksRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NewTaskViewModel(
    private val tasksRepository: TasksRepository
): ViewModel() {
    private val _fieldsError: MutableLiveData<String> = MutableLiveData()
    private val _createdTask: MutableLiveData<Task> = MutableLiveData()

    val fieldsError: LiveData<String>
        get() = _fieldsError
    val createdTask: LiveData<Task>
        get() = _createdTask

    fun addTask(name: String, description: String) = viewModelScope.launch {
        if(name.isEmpty()) {
            _fieldsError.value = "Name is required"
            return@launch
        }

        if(description.isEmpty()) {
            _fieldsError.value = "Description is required"
            return@launch
        }

        val task = Task(
            name = name,
            description = description,
            icon = 0 // TODO: Implement icons
        )

        tasksRepository.addTask(task)

        _createdTask.value = task
    }
}