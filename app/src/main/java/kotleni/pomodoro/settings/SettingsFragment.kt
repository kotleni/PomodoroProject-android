package kotleni.pomodoro.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotleni.pomodoro.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {
    private val binding: FragmentSettingsBinding by lazy { FragmentSettingsBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }
}