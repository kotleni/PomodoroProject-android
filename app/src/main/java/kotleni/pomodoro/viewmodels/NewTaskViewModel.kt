package kotleni.pomodoro.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotleni.pomodoro.RepositoriesContainer
import kotleni.pomodoro.entities.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewTaskViewModel(
    private val repositoriesContainer: RepositoriesContainer
): ViewModel() {
    private val fieldsError: MutableLiveData<String> = MutableLiveData()
    private val createdTask: MutableLiveData<Task> = MutableLiveData()

    fun getFieldsError(): MutableLiveData<String> {
        return fieldsError
    }

    fun getCreatedTask(): MutableLiveData<Task> {
        return createdTask
    }

    fun addTask(name: String, description: String) {
        if(name.isEmpty()) {
            fieldsError.value = "Name is required"
            return
        }

        if(description.isEmpty()) {
            fieldsError.value = "Description is required"
            return
        }

        val task = Task(
            name = name,
            description = description,
            icon = 0 // TODO: Implement icons
        )

        viewModelScope.launch(Dispatchers.IO) {
            repositoriesContainer.tasksRepository.addTask(task)
        }

        createdTask.value = task
    }
}