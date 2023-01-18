package kotleni.pomodoro.newtask

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar
import kotleni.pomodoro.createViewModel
import kotleni.pomodoro.databinding.FragmentNewTaskBinding
import kotleni.pomodoro.domain.repos.TasksRepository
import kotleni.pomodoro.textAsString

class NewTaskFragment : BottomSheetDialogFragment() {
    private val binding: FragmentNewTaskBinding by lazy { FragmentNewTaskBinding.inflate(layoutInflater) }
    private val viewModel: NewTaskViewModel by lazy { createViewModel { NewTaskViewModel(
        TasksRepository(requireContext()) // TODO: Use dependency injection
    ) } }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.addBtn.setOnClickListener {
            viewModel.addTask(binding.name.textAsString, binding.description.textAsString)
        }

        viewModel.fieldsError.observe(viewLifecycleOwner) {
            Snackbar.make(binding.root, it, Snackbar.LENGTH_SHORT)
                .show()
        }

        viewModel.createdTask.observe(viewLifecycleOwner) {
            setFragmentResult(REQUEST_KEY, bundleOf())
            dismiss()
        }
    }

    companion object {
        const val REQUEST_KEY = "new_task"
    }
}