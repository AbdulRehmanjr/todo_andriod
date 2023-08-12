package com.project.todofinal.components


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.project.todofinal.model.Task

var showDialog = false


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun MainScreen(){

    Scaffold (
        topBar = {header()},
        bottomBar = { addNewTask()},
        content = {
            paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
            ) {
                if(showDialog)
                {
                    NewTaskDialog(
                        onDismiss = { showDialog = false }
                    )
                }
                else
                {
                    TaskList()
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewTaskDialog(
    onDismiss: () -> Unit
) {
    var name by remember {
        mutableStateOf("")
    }

    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .padding(16.dp)
                .width(300.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                OutlinedTextField(
                    value = name,
                    onValueChange = { newValue -> name = newValue},
                )
                Button(
                    onClick = onDismiss,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Text(text = "Add Task", color = Color.White)
                }
            }
        }
    }
}
@Composable
fun addNewTask(){

        Button(
            onClick = {

            },
            modifier = Modifier
                .wrapContentHeight()
                .padding(100.dp, 5.dp)
                .shadow(elevation = 7.dp, shape = MaterialTheme.shapes.large),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "New",
                tint = Color.White
            )
            Text(text = "Add New Task")
        }
}

@Composable
fun TaskList() {

    val tasks = mutableListOf<Task>(
        Task(1, "Task 1", "Make UI/UX"),
        Task(2, "Task 2", "Build the Intro Screen"),
        Task(3, "Task 3", "Set the Resources"),
        Task(4, "Task 4", "Build the Header"),
        Task(5, "Task 5", "Build Main Screen"),
        Task(6, "Task 6", "Build edit and del Button"),
        Task(7, "Task 7", "Add list and functionality"),
    )
    LazyColumn (
    ){
        items(tasks) { task ->
            TaskItem(task)
        }
    }
}


@Composable
fun TaskItem(task: Task) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        Text(text = task.name, fontWeight = FontWeight.Bold, fontSize = 18.sp)

        Text(text = task.description, fontSize = 16.sp)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.End
        ) {

            Button(
                onClick = editTask(task),
                modifier = Modifier
                    .wrapContentHeight()
                    .shadow(elevation = 7.dp, shape = MaterialTheme.shapes.large),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit",
                    tint = Color.White
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            Button(
                onClick = {},
                modifier = Modifier
                    .wrapContentHeight()
                    .shadow(elevation = 7.dp, shape = MaterialTheme.shapes.large),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Del",
                    tint = Color.White
                )
            }
        }
    }
}

fun editTask(task: Task): ()-> Unit{

    return  {
        println(task)
    }
}

