package kotleni.pomodoro.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotleni.pomodoro.databinding.FragmentNewTaskBinding

class NewTaskFragment : BottomSheetDialogFragment() {
    private val binding: FragmentNewTaskBinding by lazy { FragmentNewTaskBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.addBtn.setOnClickListener {
            if(binding.name.text.toString().isEmpty()) {
                binding.name.error = "Name is required"
                return@setOnClickListener
            }

            if(binding.description.text.toString().isEmpty()) {
                binding.description.error = "Description is required"
                return@setOnClickListener
            }

            // todo
        }
    }
}