package site.unoeyhi.dgtodo.ui

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import site.unoeyhi.dgtodo.R
import site.unoeyhi.dgtodo.data.Todo
import site.unoeyhi.dgtodo.data.TodoDatabase

class AddTodoActivity:AppCompatActivity() {
    private lateinit var todoDatabase: TodoDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_todo_activity)

        todoDatabase = TodoDatabase.getDatabase(this)

        val inputTitle = findViewById<EditText>(R.id.inputTitle)
        val btnAdd = findViewById<Button>(R.id.btnAdd)

        btnAdd.setOnClickListener {
            val title = inputTitle.text.toString()
            if(title.isNotBlank()) {
                lifecycleScope.launch {
                    val newTodo = Todo(title = title)
                    Log.d("todo 확인", "onCreate: 추가")

                    todoDatabase.todoDao().insert(newTodo)

                    // activity move
                    setResult(RESULT_OK)
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                    finish()
                }
            }
            else {
                inputTitle.error = "타이틀 비어있음"
            }
        }
    }
}