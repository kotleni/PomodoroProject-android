package kotleni.pomodoro.tasks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.LinearLayoutManager
import kotleni.pomodoro.createViewModel
import kotleni.pomodoro.databinding.FragmentTasksBinding
import kotleni.pomodoro.domain.Task
import kotleni.pomodoro.newtask.NewTaskFragment
import kotleni.pomodoro.domain.repos.TasksRepository

class TasksFragment : Fragment() {
    private val binding: FragmentTasksBinding by lazy { FragmentTasksBinding.inflate(layoutInflater) }
    private val viewModel: TasksViewModel by lazy { createViewModel { TasksViewModel(
        TasksRepository(requireContext()) // TODO: Use dependency injection
    ) } }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = TasksAdapter()
        (binding.recyclerView.adapter as TasksAdapter).setOnItemTaskListener(object : OnItemTaskListener {
            override fun onItemTaskOpen(task: Task) {
                // TODO
            }

            override fun onItemTaskRemove(task: Task) {
                viewModel.removeTask(task)
            }
        })

        binding.newTaskFab.setOnClickListener {
            val modalBottomSheet = NewTaskFragment()
            setFragmentResultListener(NewTaskFragment.REQUEST_KEY) { _, _ ->
                viewModel.loadTasks()
            }
            modalBottomSheet.show(parentFragmentManager, "todo")
        }

        viewModel.tasksList.observe(viewLifecycleOwner) {
            (binding.recyclerView.adapter as TasksAdapter).updateTasks(it)
        }
        viewModel.loadTasks()
    }
}