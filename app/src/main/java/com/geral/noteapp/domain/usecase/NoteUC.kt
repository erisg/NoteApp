package com.geral.noteapp.domain.usecase

import com.geral.noteapp.domain.model.NoteData
import com.geral.noteapp.domain.repository.NoteRepository

class NoteUC(private val notesRepository: NoteRepository) {

    suspend fun deleteNote(noteId: Int) {
        notesRepository.deleteNote(noteId = noteId)
    }

    suspend fun updateNote(isComplete: Boolean, noteId: Int) {
        notesRepository.updateNote(isComplete = isComplete, noteId = noteId)
    }

    suspend fun insertNote(noteData: NoteData) {
        notesRepository.addNote(noteData)
    }

    suspend fun getAllNotes() = notesRepository.getAllNotes()
}
