
package com.khoirul.taskmahasiswa.ui.theme

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.khoirul.taskmahasiswa.data.Task
import com.khoirul.taskmahasiswa.data.TaskDatabase
import com.khoirul.taskmahasiswa.data.TaskRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TaskViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: TaskRepository

    val tasks: StateFlow<List<Task>>

    init {
        val taskDao = TaskDatabase.getDatabase(application).taskDao()
        repository = TaskRepository(taskDao)

        tasks = repository.allTasks.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )
    }

    fun addTask(
        mataKuliah: String,
        judul: String,
        deskripsi: String,
        deadline: String,
        prioritas: String
    ) {
        viewModelScope.launch {
            repository.insertTask(
                Task(
                    mataKuliah = mataKuliah,
                    judul = judul,
                    deskripsi = deskripsi,
                    deadline = deadline,
                    prioritas = prioritas
                )
            )
        }
    }

    fun updateTask(task: Task) {
        viewModelScope.launch {
            repository.updateTask(task)
        }
    }
    fun getTaskById(taskId: Int) =
        repository.getTaskById(taskId)

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            repository.deleteTask(task)
        }
    }
}
