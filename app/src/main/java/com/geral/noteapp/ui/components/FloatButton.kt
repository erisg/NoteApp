package com.geral.noteapp.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.geral.noteapp.R
import com.geral.noteapp.ui.theme.Pink80
import com.geral.noteapp.ui.theme.Shapes
import com.geral.noteapp.ui.theme.white

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AnimatedExtendedFloatingAction(modifier: Modifier, onClick: () -> Unit) {
    Box(modifier = modifier) {
        ExtendedFloatingActionButton(
            modifier = Modifier
                .align(alignment = Alignment.BottomEnd),
            onClick = {
                onClick.invoke()
            },
            text = { Text(text = stringResource(R.string.add_note), color = white) },
            icon = { Icon(imageVector = Icons.Filled.Add, contentDescription = "Add", tint = white) },
            shape = Shapes.medium,
            containerColor = Pink80,
        )
    }
}

@Composable
fun CheckButtonComponent(isSelected: Boolean, onClick: () -> Unit) {
    var checked by remember {
        mutableStateOf(isSelected)
    }
    Checkbox(
        checked = checked,
        onCheckedChange = { checked_ ->
            onClick.invoke()
            checked = checked_
        },
        colors = CheckboxDefaults.colors(
            checkedColor = Pink80,
        ),
    )
}
