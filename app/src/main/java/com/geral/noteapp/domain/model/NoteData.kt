package com.geral.noteapp.domain.model

data class NoteData(
    val id: Int,
    val title: String,
    val description: String,
    val isComplete: Boolean = false,
)
