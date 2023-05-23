package com.geral.noteapp.domain.repository

import com.geral.noteapp.domain.model.NoteData
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    suspend fun getAllNotes(): Flow<List<NoteData>>
    suspend fun addNote(note: NoteData)
    suspend fun deleteNote(noteId: Int)
    suspend fun updateNote(isComplete: Boolean, noteId: Int)
}
