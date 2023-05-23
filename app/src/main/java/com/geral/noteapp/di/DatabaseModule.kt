package com.geral.noteapp.di

import android.content.Context
import androidx.room.Room
import com.geral.noteapp.data.local.NotesDao
import com.geral.noteapp.data.local.NotesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun dataBaseProvides(@ApplicationContext context: Context): NotesDatabase =
        Room.databaseBuilder(context, NotesDatabase::class.java, "notes.db").build()

    @Singleton
    @Provides
    fun notesDaoProvides(
        noteDatabase: NotesDatabase,
    ): NotesDao = noteDatabase.notesDao()
}
