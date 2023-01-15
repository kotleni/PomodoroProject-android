package kotleni.pomodoro.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotleni.pomodoro.RepositoriesContainer
import kotleni.pomodoro.entities.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewTaskViewModel(
    private val repositoriesContainer: RepositoriesContainer
): ViewModel() {
    fun addTask(name: String, description: String) = viewModelScope.launch(Dispatchers.IO) {
        repositoriesContainer.tasksRepository.addTask(Task(
            name = name,
            description = description,
            icon = 0 // TODO: Implement icons
        ))
    }
}