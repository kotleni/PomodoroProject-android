package kotleni.pomodoro.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotleni.pomodoro.databinding.ItemTaskBinding

class TasksAdapter: RecyclerView.Adapter<TasksAdapter.TaskViewHolder>() {
    private var tasks = arrayListOf<String>("", "", "", "")

    class TaskViewHolder(val binding: ItemTaskBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(task: String) {
            // binding.icon.setImageResource(R.drawable.xx)
            // binding.name.text = task.name
            // binding.about.text = task.about
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(tasks[position])
    }

    override fun getItemCount(): Int {
        return tasks.size
    }
}