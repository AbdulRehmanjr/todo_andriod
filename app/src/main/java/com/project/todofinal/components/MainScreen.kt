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
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.project.todofinal.model.Task

var showDialog = false
var tasks = mutableListOf<Task>(
    Task(1, "Task 1", "Make UI/UX"),
)



@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true)
fun MainScreen() {
    var showDialog by remember { mutableStateOf(false) }
    val lazyListState = rememberLazyListState()

    Scaffold(
        topBar = { header() },
        bottomBar = { addTaskButton { showDialog = true } },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
            ) {
                if (showDialog) {
                    NewTaskDialog(
                        onDismiss = { showDialog = false },
                    )
                } else {
                    TaskList(lazyListState)
                }
            }
        }
    )
}

@Composable
fun TaskList(state: LazyListState) {
    LazyColumn(
        state = state
    ) {
        items(tasks) { task ->
            TaskItem(task)
        }
    }
}

@Composable
fun TaskItem(task: Task) {
    var showEditDialog by remember { mutableStateOf(false) }

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
                onClick = { showEditDialog = true },
                modifier = Modifier
                    .wrapContentHeight()
                    .shadow(elevation = 7.dp, shape = MaterialTheme.shapes.large),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit",
                    tint = Color.White
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            Button(
                onClick = { deleteTask(task) },
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

        if (showEditDialog) {
            EditTaskDialog(
                task = task,
                onDismiss = { showEditDialog = false }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditTaskDialog(
    task: Task,
    onDismiss: () -> Unit,
) {
    var taskName by remember { mutableStateOf(task.name) }
    var description by remember { mutableStateOf(task.description) }

    Card(
        modifier = Modifier
            .padding(50.dp)
            .width(300.dp),
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            Text(
                text = "Edit Information",
                fontWeight = FontWeight.W900,
                fontFamily = FontFamily.SansSerif,
                fontSize = 30.sp
            )

            OutlinedTextField(
                value = taskName,
                onValueChange = { newValue -> taskName = newValue },
                label = { Text("Enter task name") },
                placeholder = { Text(text = "Task X") }
            )

            OutlinedTextField(
                value = description,
                onValueChange = { newValue -> description = newValue },
                label = { Text("Enter Description") },
                placeholder = { Text(text = "Build UI") }
            )
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = {
                        editTask(task, taskName, description)
                        onDismiss()
                    },
                    modifier = Modifier
                        .padding(top = 8.dp, end = 8.dp)
                        .wrapContentHeight(),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Text(text = "Update Task", color = Color.White)
                }

                Button(
                    onClick = {
                        deleteTask(task)
                        onDismiss()
                    },
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .wrapContentHeight(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                ) {
                    Text(text = "Delete Task", color = Color.White)
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewTaskDialog(
    onDismiss: () -> Unit,
) {
    var taskName by remember {
        mutableStateOf("")
    }

    var description by remember {
        mutableStateOf("")
    }

    Card(
        modifier = Modifier
            .padding(50.dp)
            .width(300.dp),

    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            Text(
                text = "Enter Information",
                fontWeight = FontWeight.W900,
                fontFamily = FontFamily.SansSerif,
                fontSize = 30.sp
            )

            OutlinedTextField(
                value = taskName,
                onValueChange = { newValue -> taskName = newValue},
                label = { Text("Enter task name") },
                placeholder = { Text(text = "Task X") }
            )

            OutlinedTextField(
                value = description,
                onValueChange = { newValue -> description = newValue},
                label = { Text("Enter Description") },
                placeholder = { Text(text = "Build UI") }
            )
            Button(
                onClick = {
                    addTask(taskName, description)
                    onDismiss()
                          },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
            ) {
                Text(text = "Add Task", color = Color.White)
            }
        }
    }
}

@Composable
fun addTaskButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
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


fun editTask(task: Task, newName: String, newDescription: String) {
    tasks.find { it.id == task.id }?.apply {
        name = newName
        description = newDescription
    }
}

fun deleteTask(task: Task) {
    tasks.remove(task)
}

fun addTask(taskName:String,description:String){
    tasks.add(Task(tasks.size+1,taskName,description))
}
