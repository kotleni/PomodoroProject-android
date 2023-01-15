package kotleni.pomodoro.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotleni.pomodoro.databinding.ItemTaskBinding
import kotleni.pomodoro.entities.Task

class TasksAdapter: RecyclerView.Adapter<TasksAdapter.TaskViewHolder>() {
    class TaskViewHolder(val binding: ItemTaskBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(task: Task) {
            // TODO: Implement icons
            // binding.icon.setImageResource(R.drawable.xx)

            binding.name.text = task.name
            binding.description.text = task.description
        }
    }
    private var tasks = arrayListOf<Task>()

    fun updateTasks(newTasks: List<Task>) {
        tasks.clear()
        tasks.addAll(newTasks)

        // TODO: Change to DiffUtils
        notifyDataSetChanged()
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