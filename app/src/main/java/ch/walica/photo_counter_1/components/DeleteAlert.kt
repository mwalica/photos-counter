package ch.walica.photo_counter_1.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import ch.walica.photo_counter_1.DayEvents
import ch.walica.photo_counter_1.DayState
import ch.walica.photo_counter_1.ui.theme.Blue50
import ch.walica.photo_counter_1.ui.theme.BlueGrey10
import ch.walica.photo_counter_1.ui.theme.BlueGrey20
import ch.walica.photo_counter_1.ui.theme.BlueGrey90
import ch.walica.photo_counter_1.ui.theme.Orange50

@Composable
fun DeleteAlert(
    state: DayState,
    onEvent: (DayEvents) -> Unit
) {
    AlertDialog(
        containerColor = if (isSystemInDarkTheme()) BlueGrey20 else BlueGrey90,

        onDismissRequest = { onEvent(DayEvents.HideDialog) },
        confirmButton = {
            TextButton(onClick = {
                if (state.selectedDay != null) {
                    onEvent(DayEvents.DeleteDay(state.selectedDay))
                }
            }) {
                Text(
                    text = "Remove",
                    style = TextStyle(color = if (isSystemInDarkTheme()) Orange50 else Blue50)
                )
            }
        },
        dismissButton = {
            TextButton(onClick = { onEvent(DayEvents.HideDialog) }) {
                Text(
                    text = "No",
                    style = TextStyle(color = if (isSystemInDarkTheme()) Orange50 else Blue50)
                )
            }
        },
        text = {
            Text(
                "Do you want remove process image day?",
                style = TextStyle(color = MaterialTheme.colorScheme.onBackground)
            )
        }
    )
}