package com.geral.noteapp.ui.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import com.geral.noteapp.ui.theme.Shapes
import com.geral.noteapp.ui.theme.black

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldComponent(
    modifier: Modifier,
    label: String,
    placeHolder: String,
    setValue: (String) -> Unit,
) {
    var value by remember {
        mutableStateOf("")
    }
    return OutlinedTextField(
        value = value,
        onValueChange = {
            value = it
            setValue(it)
        },
        modifier = modifier,
        label = { Text(text = label) },
        placeholder = { Text(text = placeHolder) },
        shape = Shapes.medium,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = black,
            unfocusedBorderColor = Color.Gray,
        ),
    )
}
