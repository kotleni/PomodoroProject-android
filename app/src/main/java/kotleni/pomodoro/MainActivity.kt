package kotleni.pomodoro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import kotleni.pomodoro.databinding.ActivityMainBinding
import kotleni.pomodoro.fragments.SettingsFragment
import kotleni.pomodoro.fragments.TasksFragment

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.bottomNavigation.setOnItemSelectedListener {
            replaceFragment(when(it.itemId) {
                R.id.tasks -> TasksFragment()
                R.id.settings -> SettingsFragment()
                else -> throw Exception("Unknown itemId")
            })
            return@setOnItemSelectedListener true
        }

        replaceFragment(TasksFragment())
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(androidx.appcompat.R.anim.abc_fade_in, androidx.appcompat.R.anim.abc_fade_out)
            .replace(R.id.container, fragment)
            .commit()
    }
}