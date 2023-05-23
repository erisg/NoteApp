package com.geral.noteapp.data

import com.geral.noteapp.data.local.NotesDao
import com.geral.noteapp.data.local.NotesEntity
import com.geral.noteapp.data.repository.NoteRepositoryImpl
import com.geral.noteapp.domain.repository.NoteRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class NoteRepositoryTest {

    private val charactersDao = mockk<NotesDao>()
    private lateinit var noteRepository: NoteRepository

    @Before
    fun setup() {
        noteRepository = NoteRepositoryImpl(charactersDao)
    }

    @After
    fun tearDown() {
        confirmVerified(charactersDao)
    }

    @Test
    fun getLocalNotes() = runTest {
        val notesEntity = mockk<NotesEntity>()
        val notesDB = listOf(notesEntity)
        every { notesEntity.id } returns 1
        every { notesEntity.title } returns "title"
        every { notesEntity.description } returns "description"
        every { notesEntity.isComplete } returns true
        coEvery { charactersDao.getAllNotes() } returns flowOf(notesDB)
        mockkStatic("com.geral.noteapp.data.mappers.MappersKt")

        noteRepository.getAllNotes().collect { notes ->
            assertEquals(1, notes.count())
            assertEquals(1, notes.first().id)
        }

        coVerify { charactersDao.getAllNotes() }
        verify {
            notesEntity.id
            notesEntity.title
            notesEntity.description
            notesEntity.isComplete
        }
    }

    @Test
    fun `getLocalNotes should handle error`() = runTest {
        val expectedErrorMessage = "Error retrieving notes"
        coEvery { charactersDao.getAllNotes() } throws Exception(expectedErrorMessage)
        var actualError: Throwable? = null
        try {
            noteRepository.getAllNotes().collect {}
        } catch (error: Throwable) {
            actualError = error
        }
        assertEquals(expectedErrorMessage, actualError?.message)
        coVerify { charactersDao.getAllNotes() }
    }
}
