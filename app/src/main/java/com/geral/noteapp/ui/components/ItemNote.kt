package com.geral.noteapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.geral.noteapp.domain.model.NoteData
import com.geral.noteapp.ui.theme.Gray10
import com.geral.noteapp.ui.theme.Pink80
import com.geral.noteapp.ui.theme.Shapes

@Composable
fun ItemNote(
    note: NoteData,
    onClick: (Int) -> Unit,
    onClickComplete: (Boolean, Int) -> Unit,
) {
    var isSelected by remember {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier
            .height(160.dp)
            .padding(horizontal = 10.dp, vertical = 8.dp)
            .background(color = Gray10, shape = Shapes.medium)
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = note.title,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .wrapContentHeight()
                .padding(top = 15.dp, start = 16.dp),
        )
        Text(
            text = note.description,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .wrapContentHeight()
                .padding(start = 16.dp),
        )

        Row(
            modifier = Modifier.wrapContentWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom,
        ) {
            CheckButtonComponent(
                isSelected = note.isComplete,
            ) {
                if (note.isComplete) {
                    onClickComplete.invoke(false, note.id)
                } else {
                    onClickComplete.invoke(true, note.id)
                }
            }
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Menu",
                tint = Pink80,
                modifier = Modifier
                    .padding(bottom = 12.dp)
                    .clickable {
                        isSelected = true
                    },
            )
            if (isSelected) {
                CustomAlertDialog(
                    onDismiss = { isSelected = false },
                    onExit = {
                        onClick.invoke(note.id)
                        isSelected = false
                    },
                )
            }
        }
    }
}
