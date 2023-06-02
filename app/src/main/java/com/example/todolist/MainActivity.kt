package com.example.todolist

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.ui.theme.ToDoListTheme

class MainActivity : ComponentActivity() {

    private lateinit var itemAdapter: ItemAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        itemAdapter = ItemAdapter(mutableListOf())

        val toDoItems = findViewById<RecyclerView>(R.id.toDoItems)
        val addToDo = findViewById<Button>(R.id.addToDo)
        val deleteToDos = findViewById<Button>(R.id.deleteToDos)
        val toDoTitle = findViewById<EditText>(R.id.toDoTitle)

        toDoItems.adapter = itemAdapter
        toDoItems.layoutManager = LinearLayoutManager(this)

        addToDo.setOnClickListener {
            val todoTitle = toDoTitle.text.toString()
            if(todoTitle.isNotEmpty()) {
                val item = Item(todoTitle)
                itemAdapter.addItem(item)
                toDoTitle.text.clear()
            }
        }
        deleteToDos.setOnClickListener {
            itemAdapter.deleteDoneItems()
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
            text = "Hello $name!",
            modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ToDoListTheme {
        Greeting("Android")
    }
}