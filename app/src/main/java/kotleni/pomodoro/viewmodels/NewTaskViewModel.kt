package kotleni.pomodoro.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotleni.pomodoro.RepositoriesContainer
import kotleni.pomodoro.entities.Task
import kotleni.pomodoro.repos.TasksRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewTaskViewModel(
    private val tasksRepository: TasksRepository
): ViewModel() {
    private val _fieldsError: MutableLiveData<String> = MutableLiveData()
    private val _createdTask: MutableLiveData<Task> = MutableLiveData()

    val fieldsError: LiveData<String>
        get() = _fieldsError
    val createdTask: LiveData<Task>
        get() = _createdTask

    fun addTask(name: String, description: String) {
        if(name.isEmpty()) {
            _fieldsError.value = "Name is required"
            return
        }

        if(description.isEmpty()) {
            _fieldsError.value = "Description is required"
            return
        }

        val task = Task(
            name = name,
            description = description,
            icon = 0 // TODO: Implement icons
        )

        viewModelScope.launch(Dispatchers.IO) {
            tasksRepository.addTask(task)
        }

        _createdTask.value = task
    }
}