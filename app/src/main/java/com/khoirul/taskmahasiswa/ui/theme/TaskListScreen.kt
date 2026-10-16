
package com.khoirul.taskmahasiswa.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.TaskAlt
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.khoirul.taskmahasiswa.data.Task

private val MintPrimary = Color(0xFF168B70)
private val MintLight = Color(0xFFDDF7ED)
private val MintBackground = Color(0xFFF5FAF8)
private val MintText = Color(0xFF183B32)
private val MutedText = Color(0xFF71847D)
private val BorderColor = Color(0xFFE3EDE8)
private val OrangeSoft = Color(0xFFFFF0D9)
private val OrangeText = Color(0xFF9B6415)
private val BlueSoft = Color(0xFFE5F0FF)
private val BlueText = Color(0xFF285FA8)
private val RedSoft = Color(0xFFFFE8E7)
private val RedText = Color(0xFFB33F3A)

@Composable
fun TaskListScreen(
    tasks: List<Task>,
    onAddClick: () -> Unit,
    onTaskClick: (Int) -> Unit,
    onStatusChange: (Task, String) -> Unit,
    onDeleteClick: (Task) -> Unit
) {
    var selectedFilter by remember { mutableStateOf("Semua") }
    var taskToDelete by remember { mutableStateOf<Task?>(null) }

    val filters = listOf("Semua", "Belum", "Proses", "Selesai")

    val filteredTasks = if (selectedFilter == "Semua") {
        tasks
    } else {
        tasks.filter { it.status == selectedFilter }
    }

    val totalTasks = tasks.size
    val completedTasks = tasks.count { it.status == "Selesai" }
    val inProgressTasks = tasks.count { it.status == "Proses" }
    val pendingTasks = tasks.count { it.status == "Belum" }

    Scaffold(
        containerColor = MintBackground,
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddClick,
                containerColor = MintPrimary,
                contentColor = Color.White,
                shape = RoundedCornerShape(20.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Tambah tugas",
                    modifier = Modifier.size(28.dp)
                )
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = androidx.compose.foundation.layout.PaddingValues(
                    start = 20.dp,
                    top = 24.dp,
                    end = 20.dp,
                    bottom = 100.dp
                ),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(46.dp)
                                    .background(
                                        MintLight,
                                        RoundedCornerShape(15.dp)
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.TaskAlt,
                                    contentDescription = null,
                                    tint = MintPrimary,
                                    modifier = Modifier.size(27.dp)
                                )
                            }

                            Spacer(modifier = Modifier.width(12.dp))

                            Column {
                                Text(
                                    text = "Task Mahasiswa",
                                    style = MaterialTheme.typography.headlineSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = MintText
                                )
                                Text(
                                    text = "Ruang kendali tugas kuliahmu",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MutedText
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "Halo, Mahasiswa! 👋",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = MintText
                        )

                        Text(
                            text = "Atur tugasmu, selesaikan satu per satu.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MutedText
                        )
                    }
                }

                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(24.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MintPrimary
                        ),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 0.dp
                        )
                    ) {
                        Column(
                            modifier = Modifier.padding(20.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column {
                                    Text(
                                        text = "Ringkasan Tugas",
                                        style = MaterialTheme.typography.titleMedium,
                                        fontWeight = FontWeight.SemiBold,
                                        color = Color.White
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = "Pantau progres kuliahmu",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = Color.White.copy(alpha = 0.8f)
                                    )
                                }

                                Icon(
                                    imageVector = Icons.Default.MenuBook,
                                    contentDescription = null,
                                    tint = Color.White.copy(alpha = 0.9f),
                                    modifier = Modifier.size(32.dp)
                                )
                            }

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                SummaryItem(
                                    title = "Total",
                                    count = totalTasks,
                                    modifier = Modifier.weight(1f)
                                )
                                SummaryItem(
                                    title = "Proses",
                                    count = inProgressTasks,
                                    modifier = Modifier.weight(1f)
                                )
                                SummaryItem(
                                    title = "Selesai",
                                    count = completedTasks,
                                    modifier = Modifier.weight(1f)
                                )
                            }
                        }
                    }
                }

                item {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Daftar Tugas",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold,
                                color = MintText
                            )

                            Surface(
                                color = MintLight,
                                shape = RoundedCornerShape(10.dp)
                            ) {
                                Text(
                                    text = "$totalTasks tugas",
                                    modifier = Modifier.padding(
                                        horizontal = 10.dp,
                                        vertical = 6.dp
                                    ),
                                    color = MintPrimary,
                                    style = MaterialTheme.typography.labelMedium,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        }

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            filters.forEach { filter ->
                                val selected = selectedFilter == filter

                                Surface(
                                    modifier = Modifier
                                        .weight(1f)
                                        .clickable {
                                            selectedFilter = filter
                                        },
                                    shape = RoundedCornerShape(12.dp),
                                    color = if (selected) {
                                        MintPrimary
                                    } else {
                                        Color.White
                                    },
                                    border = if (selected) {
                                        null
                                    } else {
                                        androidx.compose.foundation.BorderStroke(
                                            1.dp,
                                            BorderColor
                                        )
                                    }
                                ) {
                                    Text(
                                        text = filter,
                                        modifier = Modifier.padding(
                                            horizontal = 3.dp,
                                            vertical = 11.dp
                                        ),
                                        color = if (selected) {
                                            Color.White
                                        } else {
                                            MutedText
                                        },
                                        style = MaterialTheme.typography.labelMedium,
                                        fontWeight = if (selected) {
                                            FontWeight.Bold
                                        } else {
                                            FontWeight.Medium
                                        },
                                        textAlign = androidx.compose.ui.text.style.TextAlign.Center
                                    )
                                }
                            }
                        }
                    }
                }

                if (filteredTasks.isEmpty()) {
                    item {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(22.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color.White
                            ),
                            border = androidx.compose.foundation.BorderStroke(
                                1.dp,
                                BorderColor
                            )
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(28.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(68.dp)
                                        .background(
                                            MintLight,
                                            CircleShape
                                        ),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.TaskAlt,
                                        contentDescription = null,
                                        tint = MintPrimary,
                                        modifier = Modifier.size(34.dp)
                                    )
                                }

                                Text(
                                    text = if (selectedFilter == "Semua") {
                                        "Belum ada tugas"
                                    } else {
                                        "Tidak ada tugas $selectedFilter"
                                    },
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = MintText
                                )

                                Text(
                                    text = "Tambahkan tugas kuliahmu agar semuanya tercatat rapi.",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MutedText,
                                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                                )
                            }
                        }
                    }
                } else {
                    items(
                        items = filteredTasks,
                        key = { it.id }
                    ) { task ->
                        TaskCard(
                            task = task,
                            onClick = { onTaskClick(task.id) },
                            onStatusChange = { status ->
                                onStatusChange(task, status)
                            },
                            onDeleteClick = {
                                taskToDelete = task
                            }
                        )
                    }
                }

                item {
                    Text(
                        text = "$pendingTasks tugas belum dimulai • $completedTasks tugas selesai",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 2.dp),
                        style = MaterialTheme.typography.bodySmall,
                        color = MutedText
                    )
                }
            }
        }
    }

    taskToDelete?.let { task ->
        AlertDialog(
            onDismissRequest = {
                taskToDelete = null
            },
            containerColor = Color.White,
            shape = RoundedCornerShape(24.dp),
            icon = {
                Icon(
                    imageVector = Icons.Default.DeleteOutline,
                    contentDescription = null,
                    tint = RedText,
                    modifier = Modifier.size(32.dp)
                )
            },
            title = {
                Text(
                    text = "Hapus tugas?",
                    color = MintText,
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Text(
                    text = "Tugas \"${task.judul}\" akan dihapus secara permanen.",
                    color = MutedText
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        onDeleteClick(task)
                        taskToDelete = null
                    }
                ) {
                    Text(
                        text = "Hapus",
                        color = RedText,
                        fontWeight = FontWeight.Bold
                    )
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        taskToDelete = null
                    }
                ) {
                    Text(
                        text = "Batal",
                        color = MutedText
                    )
                }
            }
        )
    }
}

@Composable
private fun SummaryItem(
    title: String,
    count: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(
                Color.White.copy(alpha = 0.15f),
                RoundedCornerShape(15.dp)
            )
            .padding(horizontal = 8.dp, vertical = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = count.toString(),
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Text(
            text = title,
            style = MaterialTheme.typography.labelSmall,
            color = Color.White.copy(alpha = 0.9f)
        )
    }
}

@Composable
private fun TaskCard(
    task: Task,
    onClick: () -> Unit,
    onStatusChange: (String) -> Unit,
    onDeleteClick: () -> Unit
) {
    var expanded by remember(task.id) {
        mutableStateOf(false)
    }

    val priorityBackground = when (task.prioritas) {
        "Tinggi" -> RedSoft
        "Rendah" -> BlueSoft
        else -> OrangeSoft
    }

    val priorityText = when (task.prioritas) {
        "Tinggi" -> RedText
        "Rendah" -> BlueText
        else -> OrangeText
    }

    val statusBackground = when (task.status) {
        "Selesai" -> MintLight
        "Proses" -> BlueSoft
        else -> OrangeSoft
    }

    val statusText = when (task.status) {
        "Selesai" -> MintPrimary
        "Proses" -> BlueText
        else -> OrangeText
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        border = androidx.compose.foundation.BorderStroke(
            1.dp,
            BorderColor
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Text(
                        text = task.mataKuliah,
                        style = MaterialTheme.typography.labelMedium,
                        color = MintPrimary,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = task.judul,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MintText
                    )
                }

                Surface(
                    color = priorityBackground,
                    shape = RoundedCornerShape(9.dp)
                ) {
                    Text(
                        text = task.prioritas,
                        modifier = Modifier.padding(
                            horizontal = 9.dp,
                            vertical = 6.dp
                        ),
                        color = priorityText,
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Event,
                    contentDescription = null,
                    tint = MutedText,
                    modifier = Modifier.size(17.dp)
                )

                Spacer(modifier = Modifier.width(6.dp))

                Text(
                    text = "Deadline: ${task.deadline}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MutedText
                )

                Spacer(modifier = Modifier.weight(1f))

                Surface(
                    color = statusBackground,
                    shape = RoundedCornerShape(9.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(
                            horizontal = 8.dp,
                            vertical = 5.dp
                        ),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        if (task.status == "Selesai") {
                            Icon(
                                imageVector = Icons.Default.CheckCircle,
                                contentDescription = null,
                                tint = statusText,
                                modifier = Modifier.size(13.dp)
                            )
                        }

                        Text(
                            text = task.status,
                            color = statusText,
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box {
                    FilledTonalButton(
                        onClick = { expanded = true }
                    ) {
                        Text("Ubah status")
                    }

                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        listOf("Belum", "Proses", "Selesai").forEach { status ->
                            DropdownMenuItem(
                                text = { Text(status) },
                                onClick = {
                                    onStatusChange(status)
                                    expanded = false
                                }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.weight(1f))

                TextButton(
                    onClick = onDeleteClick
                ) {
                    Icon(
                        imageVector = Icons.Default.DeleteOutline,
                        contentDescription = null,
                        tint = RedText,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Hapus",
                        color = RedText
                    )
                }
            }
        }
    }
}
