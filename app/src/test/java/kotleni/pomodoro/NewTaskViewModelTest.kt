package kotleni.pomodoro

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import junit.framework.TestCase.assertEquals
import kotleni.pomodoro.domain.Task
import kotleni.pomodoro.domain.repos.TasksRepository
import kotleni.pomodoro.newtask.NewTaskViewModel
import kotleni.pomodoro.tasks.TasksViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NewTaskViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    class TasksRepositoryMock: TasksRepository {
        private val tasks = arrayListOf(
            Task(
            uid = 0,
            name = "Test name",
            description = "Test description",
            icon = 0
        )
        )

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

    @Test
    fun addTask() = runTest {
        val tasksRepository = TasksRepositoryMock()
        val tasksViewModel = NewTaskViewModel(tasksRepository)
        tasksViewModel.addTask("nameee", "descriptionnn")
        assertEquals(2, tasksRepository.getTasks().size)
    }
}