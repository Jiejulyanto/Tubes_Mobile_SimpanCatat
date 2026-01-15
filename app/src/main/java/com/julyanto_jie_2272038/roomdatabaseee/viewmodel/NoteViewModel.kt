package com.julyanto_jie_2272038.roomdatabaseee.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.julyanto_jie_2272038.roomdatabaseee.database.Note
import com.julyanto_jie_2272038.roomdatabaseee.database.NoteDatabase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    private val noteDao = NoteDatabase.getDatabase(application).noteDao()

    private val _notes = MutableStateFlow<List<Note>>(emptyList())
    val notes: StateFlow<List<Note>> = _notes.asStateFlow()

    init {
        fetchAllNotes()
    }

    private fun fetchAllNotes() {
        viewModelScope.launch {
            _notes.value = noteDao.ambilSemuaCatatan()
        }
    }

    fun addNote(judul: String, isi: String) {
        viewModelScope.launch {
            val newNote = Note(judul = judul, isi = isi)
            noteDao.tambahCatatan(newNote)
            fetchAllNotes()
        }
    }

    fun updateNote(note: Note) {
        viewModelScope.launch {
            noteDao.updateCatatan(note)
            fetchAllNotes()
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            noteDao.hapusCatatan(note)
            fetchAllNotes()
        }
    }
}