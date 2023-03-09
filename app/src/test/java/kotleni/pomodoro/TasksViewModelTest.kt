package kotleni.pomodoro

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotleni.pomodoro.domain.Task
import kotleni.pomodoro.domain.repos.TasksRepository
import kotleni.pomodoro.tasks.TasksViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.*

import org.junit.Assert.*

class TasksViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    class TasksRepositoryMock: TasksRepository {
        private val tasks = arrayListOf(Task(
            uid = 0,
            name = "Test name",
            description = "Test description",
            icon = 0
        ))

        override suspend fun getTasks(): List<Task> {
            return tasks
        }

        override suspend fun addTask(task: Task) {
            tasks.add(task)
        }

        override suspend fun removeTask(task: Task) {
            tasks.remove(task)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun before() {
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun after() {
        Dispatchers.resetMain()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getTasks() = runTest {
        val tasksRepository = TasksRepositoryMock()
        val tasksViewModel = TasksViewModel(tasksRepository)
        tasksViewModel.loadTasks()
        assertEquals(tasksViewModel.tasksList.value?.size, 1)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun removeTask() = runTest {
        val tasksRepository = TasksRepositoryMock()
        val tasksViewModel = TasksViewModel(tasksRepository)
        tasksViewModel.loadTasks()
        tasksViewModel.removeTask(tasksViewModel.tasksList.value?.get(0)!!)
        assertEquals(tasksViewModel.tasksList.value?.size, 0)
    }
}