package com.geral.noteapp.data.mappers

import com.geral.noteapp.data.local.NotesEntity
import com.geral.noteapp.data.local.toNoteDomain
import com.geral.noteapp.domain.model.NoteData

fun NoteData.toNoteEntity(): NotesEntity {
    return NotesEntity(
        id = id,
        title = title,
        description = description,
        isComplete = isComplete,
    )
}
fun noteListToDomain(notesEntity: List<NotesEntity>): List<NoteData> {
    return notesEntity.map { it.toNoteDomain() }
}
