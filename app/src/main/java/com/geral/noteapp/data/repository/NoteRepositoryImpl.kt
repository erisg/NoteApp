package com.geral.noteapp.data.repository

import com.geral.noteapp.data.local.NotesDao
import com.geral.noteapp.data.mappers.noteListToDomain
import com.geral.noteapp.data.mappers.toNoteEntity
import com.geral.noteapp.domain.model.NoteData
import com.geral.noteapp.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NoteRepositoryImpl(
    private val notesDao: NotesDao,
) : NoteRepository {
    override suspend fun getAllNotes(): Flow<List<NoteData>> {
        return notesDao.getAllNotes().map { entity ->
            noteListToDomain(entity)
        }
    }

    override suspend fun addNote(note: NoteData) {
        notesDao.insertNotes(note = note.toNoteEntity())
    }

    override suspend fun deleteNote(noteId: Int) {
        notesDao.deleteNote(noteId = noteId)
    }

    override suspend fun updateNote(isComplete: Boolean, noteId: Int) {
        notesDao.updateStateNote(isComplete = isComplete, noteId = noteId)
    }
}
