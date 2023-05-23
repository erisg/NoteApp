package com.geral.noteapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.geral.noteapp.R
import com.geral.noteapp.domain.model.NoteData
import com.geral.noteapp.ui.theme.Pink80
import com.geral.noteapp.ui.theme.Shapes
import com.geral.noteapp.ui.theme.black

@Composable
fun CustomAlertDialog(onDismiss: () -> Unit, onExit: () -> Unit) {
    Dialog(
        onDismissRequest = { onDismiss() },
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false,
        ),
    ) {
        Card(
            // shape = MaterialTheme.shapes.medium,
            shape = RoundedCornerShape(10.dp),
            // modifier = modifier.size(280.dp, 240.dp)
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            elevation = CardDefaults.cardElevation(),
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .background(Color.White),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = stringResource(id = R.string.delete_message),
                    modifier = Modifier.padding(8.dp),
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                )

                Row(Modifier.padding(top = 10.dp)) {
                    OutlinedButton(
                        onClick = { onDismiss() },
                        Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .weight(1F),
                    ) {
                        Text(text = "Cancel", color = black)
                    }

                    Button(
                        onClick = { onExit() },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .weight(1F),
                        colors = ButtonDefaults.buttonColors(containerColor = Pink80),
                    ) {
                        Text(text = "Delete")
                    }
                }
            }
        }
    }
}

@Composable
fun CustomAlertDialogWithInfo(onDismiss: () -> Unit, onExit: (NoteData) -> Unit) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    Dialog(
        onDismissRequest = { onDismiss() },
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = true,
        ),
    ) {
        Card(
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(8.dp),
            elevation = CardDefaults.cardElevation(),
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(Color.White)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = stringResource(id = R.string.add_note),
                    modifier = Modifier.padding(top = 12.dp),
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                )
                TextFieldComponent(
                    modifier = Modifier.padding(8.dp),
                    label = stringResource(R.string.dialog_title),
                    placeHolder = stringResource(R.string.dialog_title),
                    setValue = { title = it },
                )
                TextFieldComponent(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    label = stringResource(R.string.dialog_description),
                    placeHolder = stringResource(R.string.dialog_description),
                    setValue = { description = it },
                )
                Row(Modifier.padding(top = 10.dp)) {
                    OutlinedButton(
                        onClick = { onDismiss() },
                        Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .weight(1F),
                        shape = Shapes.medium,
                    ) {
                        Text(text = "Cancel", color = black)
                    }

                    Button(
                        onClick = {
                            onExit(NoteData(0, title, description, false))
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .weight(1F),
                        shape = Shapes.medium,
                        colors = ButtonDefaults.buttonColors(containerColor = Pink80),
                    ) {
                        Text(text = "Add")
                    }
                }
            }
        }
    }
}
