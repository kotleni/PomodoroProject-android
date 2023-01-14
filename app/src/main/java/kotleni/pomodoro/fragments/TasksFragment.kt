package kotleni.pomodoro.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotleni.pomodoro.databinding.FragmentTasksBinding

class TasksFragment : Fragment() {
    private val binding: FragmentTasksBinding by lazy { FragmentTasksBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }
}