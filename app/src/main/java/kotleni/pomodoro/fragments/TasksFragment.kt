package kotleni.pomodoro.fragments

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotleni.pomodoro.adapters.TasksAdapter
import kotleni.pomodoro.createViewModel
import kotleni.pomodoro.databinding.FragmentTasksBinding
import kotleni.pomodoro.viewmodels.TasksViewModel

class TasksFragment : Fragment() {
    private val binding: FragmentTasksBinding by lazy { FragmentTasksBinding.inflate(layoutInflater) }
    private val viewModel: TasksViewModel by lazy { createViewModel(requireContext(), TasksViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = TasksAdapter()

        binding.newTaskFab.setOnClickListener {
            val modalBottomSheet = NewTaskFragment()
            modalBottomSheet.setOnCreatedListener { viewModel.loadTasks() }
            modalBottomSheet.show(parentFragmentManager, "todo")
        }

        viewModel.tasksList.observe(viewLifecycleOwner) {
            (binding.recyclerView.adapter as TasksAdapter).updateTasks(it)
        }
        viewModel.loadTasks()
    }
}