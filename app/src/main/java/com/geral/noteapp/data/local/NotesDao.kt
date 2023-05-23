package com.geral.noteapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNotes(note: NotesEntity)

    @Query("SELECT * FROM note ORDER BY noteId DESC")
    fun getAllNotes(): Flow<List<NotesEntity>>

    @Query("UPDATE note SET isComplete = :isComplete WHERE noteId = :noteId")
    suspend fun updateStateNote(isComplete: Boolean, noteId: Int)

    @Query("DELETE FROM note WHERE noteId =:noteId")
    suspend fun deleteNote(noteId: Int)
}
