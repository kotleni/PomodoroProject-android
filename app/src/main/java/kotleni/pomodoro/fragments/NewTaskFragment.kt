package kotleni.pomodoro.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotleni.pomodoro.createViewModel
import kotleni.pomodoro.databinding.FragmentNewTaskBinding
import kotleni.pomodoro.viewmodels.NewTaskViewModel

class NewTaskFragment : BottomSheetDialogFragment() {
    private val binding: FragmentNewTaskBinding by lazy { FragmentNewTaskBinding.inflate(layoutInflater) }
    private val viewModel: NewTaskViewModel by lazy { createViewModel(requireContext(), NewTaskViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.addBtn.setOnClickListener {
            if(binding.name.text.toString().isEmpty()) {
                binding.nameLayout.error = "Name is required"
                return@setOnClickListener
            }

            if(binding.description.text.toString().isEmpty()) {
                binding.descriptionLayout.error = "Description is required"
                return@setOnClickListener
            }

            addTask(binding.name.text.toString(), binding.description.text.toString())
        }
    }

    private fun addTask(name: String, description: String) {
        viewModel.addTask(name, description)
        dismiss()
    }
}