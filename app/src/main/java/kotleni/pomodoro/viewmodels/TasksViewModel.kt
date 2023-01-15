package kotleni.pomodoro.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotleni.pomodoro.RepositoriesContainer
import kotleni.pomodoro.entities.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TasksViewModel(
    private val repositoriesContainer: RepositoriesContainer
): ViewModel() {
    private val tasksList: MutableLiveData<List<Task>> = MutableLiveData()

    fun getTasksList(): LiveData<List<Task>> {
        return tasksList
    }

    fun loadTasks() = viewModelScope.launch(Dispatchers.IO) {
        repositoriesContainer.tasksRepository.getTasks().let {
            viewModelScope.launch(Dispatchers.Main) {
                tasksList.value = it
            }
        }
    }
}