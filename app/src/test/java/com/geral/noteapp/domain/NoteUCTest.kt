package com.geral.noteapp.domain

import com.geral.noteapp.domain.model.NoteData
import com.geral.noteapp.domain.repository.NoteRepository
import com.geral.noteapp.domain.usecase.NoteUC
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class NoteUCTest {
    private val notesRepository: NoteRepository = mockk()

    private lateinit var noteUC: NoteUC

    @Before
    fun setup() {
        noteUC = NoteUC(notesRepository)
    }

    @Test
    fun getLocalCharacters() = runTest {
        val characters = listOf<NoteData>()
        coEvery { notesRepository.getAllNotes() } returns flowOf(characters)

        noteUC.getAllNotes()

        coVerify { notesRepository.getAllNotes() }
    }
}
