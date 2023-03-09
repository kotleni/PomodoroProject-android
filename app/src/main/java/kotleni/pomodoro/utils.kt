package kotleni.pomodoro

import android.content.Context
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.get
import kotleni.pomodoro.domain.repos.TasksRepository

/** Create viewmodel for viewmodelstoreowner
 * @param builder Lambda function that creates viewmodel
 * @return Instance of viewmodel
 */
@Suppress("UNCHECKED_CAST")
inline fun <reified T: ViewModel> ViewModelStoreOwner.createViewModel(builder: () -> T): T {
    val instance = builder()

    val factory = object : ViewModelProvider.Factory {
        override fun <T: ViewModel> create(modelClass: Class<T>): T {
            return instance as T
        }
    }

    return ViewModelProvider(this, factory).get() as T
}

/* Get textview or edittext text as string */
val TextView.textAsString: String
    get() = text.toString()