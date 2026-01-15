package com.julyanto_jie_2272038.roomdatabaseee.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.julyanto_jie_2272038.roomdatabaseee.database.Note
import com.julyanto_jie_2272038.roomdatabaseee.ui.components.CatatanItem
import com.julyanto_jie_2272038.roomdatabaseee.viewmodel.NoteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpanCatatScreen(viewModel: NoteViewModel = viewModel()) {

    val notes by viewModel.notes.collectAsState()

    var judul by remember { mutableStateOf("") }
    var isi by remember { mutableStateOf("") }


    var showEditDialog by remember { mutableStateOf(false) }
    var editingNote by remember { mutableStateOf<Note?>(null) }

    if (showEditDialog && editingNote != null) {
        var editedJudul by remember { mutableStateOf(editingNote!!.judul) }
        var editedIsi by remember { mutableStateOf(editingNote!!.isi) }

        AlertDialog(
            onDismissRequest = { showEditDialog = false },
            title = { Text("Edit Catatan") },
            text = {
                Column {
                    OutlinedTextField(
                        value = editedJudul,
                        onValueChange = { editedJudul = it },
                        label = { Text("Judul") }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = editedIsi,
                        onValueChange = { editedIsi = it },
                        label = { Text("Isi") }
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        val updatedNote = editingNote!!.copy(judul = editedJudul, isi = editedIsi)
                        viewModel.updateNote(updatedNote)
                        showEditDialog = false
                    }
                ) {
                    Text("Simpan Perubahan")
                }
            },
            dismissButton = {
                Button(onClick = { showEditDialog = false }) {
                    Text("Batal")
                }
            }
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("SimpanCatat (CRUD)") }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = judul,
                onValueChange = { judul = it },
                label = { Text("Judul Catatan Baru") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = isi,
                onValueChange = { isi = it },
                label = { Text("Isi Catatan Baru") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    if (judul.isNotBlank() && isi.isNotBlank()) {
                        viewModel.addNote(judul, isi)
                        // Reset fields
                        judul = ""
                        isi = ""
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Simpan Catatan Baru")
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text("Daftar Catatan", style = MaterialTheme.typography.headlineSmall)
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(notes) { note ->
                    CatatanItem(
                        note = note,
                        onEditClick = {
                            editingNote = note
                            showEditDialog = true
                        },
                        onDeleteClick = {
                            viewModel.deleteNote(note)
                        }
                    )
                }
            }
        }
    }
}