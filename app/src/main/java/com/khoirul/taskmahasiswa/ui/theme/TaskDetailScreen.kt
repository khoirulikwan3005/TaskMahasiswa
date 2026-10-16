
package com.khoirul.taskmahasiswa.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.TaskAlt
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.khoirul.taskmahasiswa.data.Task

private val DetailMint = Color(0xFF43BFA0)
private val DetailBackground = Color(0xFFF5F9F7)
private val DetailText = Color(0xFF24352F)
private val DetailMuted = Color(0xFF788780)
private val DetailBorder = Color(0xFFE1ECE6)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskDetailScreen(
    task: Task,
    onEdit: () -> Unit,
    onBack: () -> Unit
) {
    val priorityColor = when (task.prioritas.lowercase()) {
        "tinggi" -> Color(0xFFD9534F)
        "rendah" -> Color(0xFF3D9B75)
        else -> Color(0xFFE5A23B)
    }

    val isDone = task.status.equals("Selesai", ignoreCase = true)

    Scaffold(
        containerColor = DetailBackground,
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = "Detail Tugas",
                            fontWeight = FontWeight.Bold,
                            color = DetailText
                        )
                        Text(
                            text = "Informasi lengkap tugasmu",
                            style = MaterialTheme.typography.bodySmall,
                            color = DetailMuted
                        )
                    }
                },
                navigationIcon = {
                    OutlinedButton(
                        onClick = onBack,
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
                .padding(horizontal = 20.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(26.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(2.dp)
            ) {
                Column(
                    modifier = Modifier.padding(22.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = "JUDUL TUGAS",
                                style = MaterialTheme.typography.labelSmall,
                                color = DetailMuted,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(Modifier.height(6.dp))
                            Text(
                                text = task.judul,
                                style = MaterialTheme.typography.headlineSmall,
                                fontWeight = FontWeight.Bold,
                                color = DetailText
                            )
                        }

                        Icon(
                            imageVector = Icons.Default.TaskAlt,
                            contentDescription = null,
                            tint = DetailMint,
                            modifier = Modifier
                                .background(
                                    color = Color(0xFFE5F7F0),
                                    shape = CircleShape
                                )
                                .padding(12.dp)
                        )
                    }

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        DetailBadge(
                            text = task.prioritas,
                            color = priorityColor
                        )
                        DetailBadge(
                            text = task.status,
                            color = if (isDone) {
                                Color(0xFF27845F)
                            } else {
                                Color(0xFF647B70)
                            }
                        )
                    }
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
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    Text(
                        text = "Informasi Tugas",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = DetailText
                    )

                    DetailInfoRow(
                        icon = {
                            Icon(
                                Icons.Default.MenuBook,
                                contentDescription = null,
                                tint = DetailMint
                            )
                        },
                        label = "Mata Kuliah",
                        value = task.mataKuliah
                    )

                    DetailInfoRow(
                        icon = {
                            Icon(
                                Icons.Default.CalendarMonth,
                                contentDescription = null,
                                tint = DetailMint
                            )
                        },
                        label = "Deadline",
                        value = task.deadline
                    )

                    DetailInfoRow(
                        icon = {
                            Icon(
                                Icons.Default.Flag,
                                contentDescription = null,
                                tint = priorityColor
                            )
                        },
                        label = "Prioritas",
                        value = task.prioritas
                    )

                    DetailInfoRow(
                        icon = {
                            Icon(
                                Icons.Default.CheckCircle,
                                contentDescription = null,
                                tint = if (isDone) DetailMint else DetailMuted
                            )
                        },
                        label = "Status Pengerjaan",
                        value = task.status
                    )

                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = "Deskripsi",
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.Bold,
                            color = DetailMuted
                        )

                        Text(
                            text = task.deskripsi.ifBlank {
                                "Tidak ada deskripsi untuk tugas ini."
                            },
                            style = MaterialTheme.typography.bodyLarge,
                            color = DetailText
                        )
                    }
                }
            }

            Spacer(Modifier.weight(1f))

            Button(
                onClick = onEdit,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                shape = RoundedCornerShape(17.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = DetailMint,
                    contentColor = Color.White
                )
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = null
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    text = "Edit Tugas",
                    fontWeight = FontWeight.Bold
                )
            }

            OutlinedButton(
                onClick = onBack,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(17.dp)
            ) {
                Text(
                    text = "Kembali ke Daftar",
                    color = DetailText
                )
            }

            Spacer(Modifier.height(4.dp))
        }
    }
}

@Composable
private fun DetailBadge(
    text: String,
    color: Color
) {
    Row(
        modifier = Modifier
            .background(
                color = color.copy(alpha = 0.12f),
                shape = RoundedCornerShape(10.dp)
            )
            .padding(horizontal = 12.dp, vertical = 7.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            color = color,
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun DetailInfoRow(
    icon: @Composable () -> Unit,
    label: String,
    value: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top
    ) {
        icon()

        Spacer(Modifier.width(12.dp))

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.bodySmall,
                color = DetailMuted
            )
            Text(
                text = value.ifBlank { "-" },
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold,
                color = DetailText
            )
        }
    }
}
