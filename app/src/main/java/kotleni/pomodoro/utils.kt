package kotleni.pomodoro

import android.content.Context
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.get
import kotleni.pomodoro.repos.TasksRepository

/** Create viewmodel for viewmodelstoreowner
 * @param context Activity context
 * @param clazz Viewmodel class
 * @return Initialized viewmodel
 */
fun <T: ViewModel> ViewModelStoreOwner.createViewModel(context: Context, clazz: Class<T>): T {
    val constructor = clazz.getConstructor(RepositoriesContainer::class.java)
    val instance = constructor.newInstance(RepositoriesContainer(
        TasksRepository(context)
    ))

    val factory = object : ViewModelProvider.Factory {
        override fun <T: ViewModel> create(modelClass: Class<T>): T {
            return instance as T
        }
    }

    return (ViewModelProvider(this, factory).get() as ViewModel) as T
}

/* Get textview or edittext text as string */
val TextView.textAsString: String
    get() = text.toString()