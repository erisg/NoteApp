package com.geral.noteapp.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.geral.noteapp.domain.model.NoteData

@Entity(tableName = "note")
data class NotesEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "noteId")
    val id: Int,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "isComplete")
    val isComplete: Boolean,
)

fun NotesEntity.toNoteDomain(): NoteData {
    return NoteData(
        id = id,
        title = title,
        description = description,
        isComplete = isComplete,
    )
}
