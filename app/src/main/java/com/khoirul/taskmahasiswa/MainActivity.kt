
package com.khoirul.taskmahasiswa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.khoirul.taskmahasiswa.navigation.TaskNavigation
import com.khoirul.taskmahasiswa.ui.theme.TaskDetailScreen
import com.khoirul.taskmahasiswa.ui.theme.TaskFormScreen
import com.khoirul.taskmahasiswa.ui.theme.TaskListScreen
import com.khoirul.taskmahasiswa.ui.theme.TaskViewModel
import com.khoirul.taskmahasiswa.ui.theme.TaskViewModelFactory
import com.khoirul.taskmahasiswa.ui.theme.TaskMahasiswaTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TaskMahasiswaTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    val taskViewModel: TaskViewModel = viewModel(
                        factory = TaskViewModelFactory(application)
                    )

                    val tasks by taskViewModel.tasks.collectAsState()

                    NavHost(
                        navController = navController,
                        startDestination = TaskNavigation.LIST
                    ) {
                        composable(TaskNavigation.LIST) {
                            TaskListScreen(
                                tasks = tasks,
                                onAddClick = {
                                    navController.navigate(TaskNavigation.ADD)
                                },
                                onTaskClick = { taskId ->
                                    navController.navigate(
                                        "${TaskNavigation.DETAIL}/$taskId"
                                    )
                                },
                                onStatusChange = { task, status ->
                                    taskViewModel.updateTask(
                                        task.copy(status = status)
                                    )
                                },
                                onDeleteClick = { task ->
                                    taskViewModel.deleteTask(task)
                                }
                            )
                        }

                        composable(TaskNavigation.ADD) {
                            TaskFormScreen(
                                onSave = { task ->
                                    taskViewModel.addTask(
                                        mataKuliah = task.mataKuliah,
                                        judul = task.judul,
                                        deskripsi = task.deskripsi,
                                        deadline = task.deadline,
                                        prioritas = task.prioritas
                                    )
                                    navController.popBackStack()
                                },
                                onCancel = {
                                    navController.popBackStack()
                                }
                            )
                        }

                        composable(
                            route = "${TaskNavigation.DETAIL}/{taskId}",
                            arguments = listOf(
                                navArgument("taskId") {
                                    type = NavType.IntType
                                }
                            )
                        ) { entry ->
                            val taskId = entry.arguments?.getInt("taskId") ?: 0
                            val task by taskViewModel.getTaskById(taskId)
                                .collectAsState(initial = null)

                            if (task != null) {
                                TaskDetailScreen(
                                    task = task!!,
                                    onEdit = {
                                        navController.navigate(
                                            "${TaskNavigation.EDIT}/$taskId"
                                        )
                                    },
                                    onBack = {
                                        navController.popBackStack()
                                    }
                                )
                            } else {
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    CircularProgressIndicator()
                                }
                            }
                        }

                        composable(
                            route = "${TaskNavigation.EDIT}/{taskId}",
                            arguments = listOf(
                                navArgument("taskId") {
                                    type = NavType.IntType
                                }
                            )
                        ) { entry ->
                            val taskId = entry.arguments?.getInt("taskId") ?: 0
                            val task by taskViewModel.getTaskById(taskId)
                                .collectAsState(initial = null)

                            if (task != null) {
                                TaskFormScreen(
                                    initialTask = task,
                                    onSave = { updatedTask ->
                                        taskViewModel.updateTask(updatedTask)
                                        navController.popBackStack(
                                            TaskNavigation.LIST,
                                            inclusive = false
                                        )
                                    },
                                    onCancel = {
                                        navController.popBackStack()
                                    }
                                )
                            } else {
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    CircularProgressIndicator()
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
