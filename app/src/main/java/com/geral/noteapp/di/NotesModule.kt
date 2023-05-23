package com.geral.noteapp.di

import com.geral.noteapp.data.local.NotesDao
import com.geral.noteapp.data.repository.NoteRepositoryImpl
import com.geral.noteapp.domain.repository.NoteRepository
import com.geral.noteapp.domain.usecase.NoteUC
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object NotesModule {

    @Provides
    @ViewModelScoped
    fun getNotesUCProvider(
        noteRepository: NoteRepository,
    ): NoteUC = NoteUC(noteRepository)

    @Provides
    @ViewModelScoped
    fun notesRepositoryProvider(
        notesDao: NotesDao,
    ): NoteRepository = NoteRepositoryImpl(notesDao)
}
