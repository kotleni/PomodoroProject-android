package kotleni.pomodoro.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotleni.pomodoro.adapters.TasksAdapter
import kotleni.pomodoro.databinding.FragmentTasksBinding

class TasksFragment : Fragment() {
    private val binding: FragmentTasksBinding by lazy { FragmentTasksBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = TasksAdapter()
    }
}