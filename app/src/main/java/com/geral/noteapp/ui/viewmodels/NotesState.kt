package com.geral.noteapp.ui.viewmodels

import androidx.annotation.StringRes
import com.geral.noteapp.domain.model.NoteData

sealed class NotesState {
    object Loading : NotesState()
    data class NotesSuccess(val notes: List<NoteData>) : NotesState()
    data class NotesError(@StringRes val errorMessage: Int? = null) : NotesState()
}
