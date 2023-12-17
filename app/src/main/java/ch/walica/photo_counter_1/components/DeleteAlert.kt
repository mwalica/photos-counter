package ch.walica.photo_counter_1.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import ch.walica.photo_counter_1.DayEvents
import ch.walica.photo_counter_1.DayState

@Composable
fun DeleteAlert(
    state: DayState,
    onEvent: (DayEvents) -> Unit
) {
    AlertDialog(
        onDismissRequest = { onEvent(DayEvents.HideDialog) },
        confirmButton = {
            Button(onClick = {
                if (state.selectedDay != null) {
                    onEvent(DayEvents.DeleteDay(state.selectedDay))
                }
            }) {
                Text(text = "Remove")
            }
        },
        dismissButton = {
            Button(onClick = { onEvent(DayEvents.HideDialog) }) {
                Text(text = "No")
            }
        },
        text = { Text("Do you want remove process image day?") }
    )
}