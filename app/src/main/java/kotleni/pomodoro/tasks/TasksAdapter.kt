package kotleni.pomodoro.tasks

import android.view.*
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import kotleni.pomodoro.R
import kotleni.pomodoro.databinding.ItemTaskBinding
import kotleni.pomodoro.domain.Task

class TasksAdapter: RecyclerView.Adapter<TasksAdapter.TaskViewHolder>(), OnItemTaskListener {
    class TaskViewHolder(val binding: ItemTaskBinding, val listener: OnItemTaskListener): RecyclerView.ViewHolder(binding.root), PopupMenu.OnMenuItemClickListener  {
        private var bindedTask: Task? = null

        fun bind(task: Task) {
            bindedTask = task

            // TODO: Implement icons
            // binding.icon.setImageResource(R.drawable.xx)

            binding.name.text = task.name
            binding.description.text = task.description

            val popup = PopupMenu(binding.root.context, binding.card)
            popup.setOnMenuItemClickListener(this)
            val inflater: MenuInflater = popup.menuInflater
            inflater.inflate(R.menu.tasks_item_actions, popup.menu)

            binding.card.setOnLongClickListener {
                popup.show()
                return@setOnLongClickListener true
            }
        }

        override fun onMenuItemClick(item: MenuItem): Boolean {
            val bindedTask = bindedTask ?: return false

            when(item.itemId) {
                R.id.open -> { listener.onItemTaskOpen(bindedTask) }
                R.id.remove -> { listener.onItemTaskRemove(bindedTask) }
            }
            return true
        }
    }

    private var tasks = arrayListOf<Task>()
    private var onItemTaskListener: OnItemTaskListener? = null

    fun setOnItemTaskListener(listener: OnItemTaskListener) {
        onItemTaskListener = listener
    }

    fun updateTasks(newTasks: List<Task>) {
        tasks.clear()
        tasks.addAll(newTasks)

        // TODO: Change to DiffUtils
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(binding, this)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(tasks[position])
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    override fun onItemTaskOpen(task: Task) {
        onItemTaskListener?.onItemTaskOpen(task)
    }

    override fun onItemTaskRemove(task: Task) {
        onItemTaskListener?.onItemTaskRemove(task)
    }
}