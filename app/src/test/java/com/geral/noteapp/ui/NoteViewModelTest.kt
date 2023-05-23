package com.geral.noteapp.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.geral.noteapp.domain.CoroutineRule
import com.geral.noteapp.domain.model.NoteData
import com.geral.noteapp.domain.usecase.NoteUC
import com.geral.noteapp.ui.viewmodels.NotesState
import com.geral.noteapp.ui.viewmodels.NotesViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class NoteViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = CoroutineRule()

    private val notesUC = mockk<NoteUC>(relaxed = true)

    private lateinit var noteViewModel: NotesViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        noteViewModel = NotesViewModel(notesUC)
    }

    @Before
    fun tearDown() {
        confirmVerified(notesUC)
    }

    @Test
    fun `loadNotes should update notes StateFlow with success state and empty list`() = runBlockingTest {
        // Arrange
        val notesList = emptyList<NoteData>()
        val expectedState = NotesState.NotesSuccess(notesList)
        coEvery { notesUC.getAllNotes() } returns flowOf(notesList)
        noteViewModel.loadNotes()
        assertEquals(expectedState, noteViewModel.notes.value)
    }
}
