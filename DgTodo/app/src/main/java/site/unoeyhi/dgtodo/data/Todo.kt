package site.unoeyhi.dgtodo.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo (
    @PrimaryKey(autoGenerate = true)
    val num: Int = 0,
    val title: String,
    val completed: Boolean = false
)
