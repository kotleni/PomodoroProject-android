package kotleni.pomodoro.entities

import android.app.Activity
import android.view.View
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,

    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "icon") val icon: Int,

    @ColumnInfo("total_breaks_time") val totalBreaksTime: Int = 0, // in secs
    @ColumnInfo("total_flows_time") val totalFlowsTime: Int = 0,   // in secs
) {
    val totalTime: Int get() = totalBreaksTime + totalFlowsTime          // in secs
}