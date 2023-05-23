package com.geral.noteapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geral.noteapp.domain.model.NoteData
import com.geral.noteapp.domain.usecase.NoteUC
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val noteUC: NoteUC,
) : ViewModel() {
    private val _notes = MutableStateFlow(NotesState.NotesSuccess(emptyList()))
    val notes: StateFlow<NotesState> = _notes.asStateFlow()

    fun loadNotes() {
        viewModelScope.launch {
            noteUC.getAllNotes().collect { notes ->
                _notes.value = NotesState.NotesSuccess(notes)
            }
        }
    }

    fun saveNote(noteData: NoteData) {
        viewModelScope.launch {
            noteUC.insertNote(noteData)
        }
    }

    fun deleteNote(noteId: Int) {
        viewModelScope.launch {
            noteUC.deleteNote(noteId)
        }
    }

    fun updateNote(isComplete: Boolean, noteId: Int) {
        viewModelScope.launch {
            noteUC.updateNote(isComplete, noteId)
        }
    }
}
