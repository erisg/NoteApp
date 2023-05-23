package com.geral.noteapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import com.geral.noteapp.R
import com.geral.noteapp.domain.model.NoteData
import com.geral.noteapp.ui.components.AnimatedExtendedFloatingAction
import com.geral.noteapp.ui.components.CustomAlertDialogWithInfo
import com.geral.noteapp.ui.components.ItemNote
import com.geral.noteapp.ui.components.LoadingShimmerEffect
import com.geral.noteapp.ui.theme.NoteAppTheme
import com.geral.noteapp.ui.theme.Purple80
import com.geral.noteapp.ui.theme.white
import com.geral.noteapp.ui.viewmodels.NotesState
import com.geral.noteapp.ui.viewmodels.NotesViewModel

@Composable
fun NoteScreen(
    noteViewModel: NotesViewModel,
    onClick: () -> Unit,
) {
    val notesState: NotesState by noteViewModel.notes.collectAsState(initial = NotesState.Loading)
    val state by remember(key1 = notesState) {
        mutableStateOf(notesState)
    }
    var isOpenAddNote by remember {
        mutableStateOf(false)
    }
    if (isOpenAddNote) {
        CustomAlertDialogWithInfo(
            onDismiss = { isOpenAddNote = false },
            onExit = {
                noteViewModel.saveNote(it)
                onClick.invoke()
                isOpenAddNote = false
            },
        )
    }

    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (
            topImage,
            spacer,
            title,
            floating,
            notes,
        ) = createRefs()
        Image(
            painter = painterResource(R.drawable.background),
            contentDescription = "background",
            modifier = Modifier.constrainAs(topImage) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
            },
        )
        Spacer(
            modifier = Modifier
                .constrainAs(spacer) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .height(24.dp),
        )
        Text(
            text = stringResource(id = R.string.welcome_message),
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start,
            color = white,
            modifier = Modifier
                .wrapContentHeight()
                .constrainAs(title) {
                    top.linkTo(spacer.bottom)
                    start.linkTo(parent.start)
                }.padding(start = 24.dp, bottom = 24.dp),
        )
        when (state) {
            is NotesState.Loading -> {
                LazyColumn(
                    modifier = Modifier
                        .wrapContentSize()
                        .constrainAs(notes) {
                            top.linkTo(title.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            bottom.linkTo(floating.bottom)
                        },
                ) {
                    items(4) {
                        LoadingShimmerEffect()
                    }
                }
            }
            is NotesState.NotesSuccess -> {
                if ((state as NotesState.NotesSuccess).notes.isEmpty()) {
                    ChargeMessage(
                        message = stringResource(id = R.string.empty_notes_message),
                        modifier = Modifier
                            .wrapContentSize()
                            .constrainAs(notes) {
                                top.linkTo(title.bottom)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                                bottom.linkTo(floating.bottom)
                            },
                    )
                } else {
                    ChargeNotes(
                        notesData = (state as NotesState.NotesSuccess).notes,
                        modifier = Modifier
                            .padding(top = 34.dp, bottom = 24.dp)
                            .fillMaxHeight()
                            .constrainAs(notes) {
                                top.linkTo(title.bottom)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                                bottom.linkTo(parent.bottom)
                            },
                        onClick = { noteViewModel.deleteNote(it) },
                        onClickComplete = { isComplete, id ->
                            noteViewModel.updateNote(isComplete, id)
                        },
                    )
                }
            }
            is NotesState.NotesError -> {
                ChargeMessage(
                    message = stringResource(id = R.string.error_message),
                    modifier = Modifier
                        .wrapContentHeight()
                        .fillMaxWidth()
                        .padding(vertical = 10.dp)
                        .constrainAs(notes) {
                            top.linkTo(title.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            bottom.linkTo(floating.bottom)
                        },
                )
            }
        }
        AnimatedExtendedFloatingAction(
            modifier = Modifier
                .fillMaxSize()
                .padding(end = 16.dp, bottom = 16.dp)
                .constrainAs(floating) {
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                },
            onClick = {
                isOpenAddNote = true
            },
        )
    }
}

@Composable
fun ChargeNotes(
    notesData: List<NoteData>,
    modifier: Modifier,
    onClick: (Int) -> Unit,
    onClickComplete: (Boolean, Int) -> Unit,
) {
    LazyColumn(
        modifier = modifier,
    ) {
        items(notesData) { note ->
            ItemNote(
                note,
                onClick = { onClick.invoke(it) },
                onClickComplete = { isComplete, id ->
                    onClickComplete.invoke(isComplete, id)
                },
            )
        }
    }
}

@Composable
fun ChargeMessage(message: String, modifier: Modifier) {
    Text(
        text = message,
        fontSize = 26.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Start,
        color = Purple80,
        modifier = modifier,
    )
}

@Preview
@Composable
fun NoteScreenScreen() {
    NoteAppTheme {
        NoteScreen(viewModel(), {})
    }
}
