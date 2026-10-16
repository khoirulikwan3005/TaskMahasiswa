
package com.khoirul.taskmahasiswa.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Title
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.khoirul.taskmahasiswa.data.Task

private val FormMint = Color(0xFF43BFA0)
private val FormBackground = Color(0xFFF5F9F7)
private val FormText = Color(0xFF24352F)
private val FormMuted = Color(0xFF788780)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskFormScreen(
    initialTask: Task? = null,
    onSave: (Task) -> Unit,
    onCancel: () -> Unit
) {
    var mataKuliah by remember(initialTask?.id) {
        mutableStateOf(initialTask?.mataKuliah ?: "")
    }
    var judul by remember(initialTask?.id) {
        mutableStateOf(initialTask?.judul ?: "")
    }
    var deskripsi by remember(initialTask?.id) {
        mutableStateOf(initialTask?.deskripsi ?: "")
    }
    var deadline by remember(initialTask?.id) {
        mutableStateOf(initialTask?.deadline ?: "")
    }
    var prioritas by remember(initialTask?.id) {
        mutableStateOf(initialTask?.prioritas ?: "Sedang")
    }
    var errorMessage by remember {
        mutableStateOf("")
    }

    val isEdit = initialTask != null

    Scaffold(
        containerColor = FormBackground,
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = if (isEdit) "Edit Tugas" else "Tambah Tugas",
                            fontWeight = FontWeight.Bold,
                            color = FormText
                        )
                        Text(
                            text = if (isEdit)
                                "Perbarui detail tugasmu"
                            else
                                "Catat tugas agar tidak terlewat",
                            style = MaterialTheme.typography.bodySmall,
                            color = FormMuted
                        )
                    }
                },
                navigationIcon = {
                    OutlinedButton(
                        onClick = onCancel,
                        modifier = Modifier.padding(start = 8.dp),
                        shape = RoundedCornerShape(14.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Kembali"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(1.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    Text(
                        text = "Informasi Tugas",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = FormText
                    )

                    Text(
                        text = "Lengkapi informasi tugas kuliahmu.",
                        style = MaterialTheme.typography.bodySmall,
                        color = FormMuted
                    )

                    OutlinedTextField(
                        value = mataKuliah,
                        onValueChange = {
                            mataKuliah = it
                            errorMessage = ""
                        },
                        modifier = Modifier.fillMaxWidth(),
                        label = { Text("Mata Kuliah") },
                        leadingIcon = {
                            Icon(Icons.Default.MenuBook, contentDescription = null)
                        },
                        shape = RoundedCornerShape(16.dp),
                        singleLine = true
                    )

                    OutlinedTextField(
                        value = judul,
                        onValueChange = {
                            judul = it
                            errorMessage = ""
                        },
                        modifier = Modifier.fillMaxWidth(),
                        label = { Text("Judul Tugas") },
                        leadingIcon = {
                            Icon(Icons.Default.Title, contentDescription = null)
                        },
                        shape = RoundedCornerShape(16.dp),
                        singleLine = true
                    )

                    OutlinedTextField(
                        value = deskripsi,
                        onValueChange = { deskripsi = it },
                        modifier = Modifier.fillMaxWidth(),
                        label = { Text("Deskripsi Tugas") },
                        leadingIcon = {
                            Icon(Icons.Default.Description, contentDescription = null)
                        },
                        shape = RoundedCornerShape(16.dp),
                        minLines = 3,
                        maxLines = 5
                    )

                    OutlinedTextField(
                        value = deadline,
                        onValueChange = {
                            deadline = it
                            errorMessage = ""
                        },
                        modifier = Modifier.fillMaxWidth(),
                        label = { Text("Deadline") },
                        placeholder = { Text("Contoh: 20 Oktober 2026") },
                        leadingIcon = {
                            Icon(Icons.Default.CalendarMonth, contentDescription = null)
                        },
                        shape = RoundedCornerShape(16.dp),
                        singleLine = true
                    )
                }
            }

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(1.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = "Prioritas Tugas",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = FormText
                    )

                    Text(
                        text = "Tentukan tingkat kepentingan tugas.",
                        style = MaterialTheme.typography.bodySmall,
                        color = FormMuted
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        listOf("Rendah", "Sedang", "Tinggi").forEach { item ->
                            if (prioritas == item) {
                                Button(
                                    onClick = { prioritas = item },
                                    modifier = Modifier.weight(1f),
                                    shape = RoundedCornerShape(14.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = FormMint
                                    ),
                                    contentPadding = androidx.compose.foundation.layout.PaddingValues(
                                        horizontal = 2.dp,
                                        vertical = 10.dp
                                    )
                                ) {
                                    Text(item)
                                }
                            } else {
                                OutlinedButton(
                                    onClick = { prioritas = item },
                                    modifier = Modifier.weight(1f),
                                    shape = RoundedCornerShape(14.dp),
                                    contentPadding = androidx.compose.foundation.layout.PaddingValues(
                                        horizontal = 2.dp,
                                        vertical = 10.dp
                                    )
                                ) {
                                    Text(item, color = FormText)
                                }
                            }
                        }
                    }
                }
            }

            if (errorMessage.isNotBlank()) {
                Text(
                    text = errorMessage,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Button(
                onClick = {
                    if (
                        mataKuliah.isBlank() ||
                        judul.isBlank() ||
                        deadline.isBlank()
                    ) {
                        errorMessage =
                            "Mata kuliah, judul, dan deadline wajib diisi."
                    } else {
                        val savedTask = Task(
                            id = initialTask?.id ?: 0,
                            mataKuliah = mataKuliah.trim(),
                            judul = judul.trim(),
                            deskripsi = deskripsi.trim(),
                            deadline = deadline.trim(),
                            prioritas = prioritas,
                            status = initialTask?.status ?: "Belum"
                        )
                        onSave(savedTask)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(18.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = FormMint,
                    contentColor = Color.White
                )
            ) {
                Icon(Icons.Default.Save, contentDescription = null)
                Spacer(Modifier.padding(horizontal = 4.dp))
                Text(
                    text = if (isEdit) "Simpan Perubahan" else "Simpan Tugas",
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(Modifier.height(8.dp))
        }
    }
}
