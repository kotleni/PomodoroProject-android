package kotleni.pomodoro.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar
import kotleni.pomodoro.createViewModel
import kotleni.pomodoro.databinding.FragmentNewTaskBinding
import kotleni.pomodoro.textAsString
import kotleni.pomodoro.viewmodels.NewTaskViewModel

class NewTaskFragment : BottomSheetDialogFragment() {
    private val binding: FragmentNewTaskBinding by lazy { FragmentNewTaskBinding.inflate(layoutInflater) }
    private val viewModel: NewTaskViewModel by lazy { createViewModel(requireContext(), NewTaskViewModel::class.java) }
    private var onCreated: (() -> Unit)? = null

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
            onCreated?.invoke()
            dismiss()
        }
    }

    fun setOnCreatedListener(onCreated: () -> Unit) {
        this.onCreated = onCreated
    }
}