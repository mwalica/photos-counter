package ch.walica.photo_counter_1.views

import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ch.walica.photo_counter_1.DayEvents
import ch.walica.photo_counter_1.DayState
import ch.walica.photo_counter_1.R
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    state: DayState,
    onEvent: (DayEvents) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Photos counter") },
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { onEvent(DayEvents.InsertDay) }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "add day")
            }
        }
    ) { padding ->

        LazyColumn(
            contentPadding = padding,
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                Text(text = "count of picture: ${state.days.sumOf { it.amount }}")
            }

            items(state.days) { day ->
                val date =
                    ZonedDateTime.ofInstant(Instant.ofEpochSecond(day.date), ZoneId.systemDefault())
                val formattedDate =
                    DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.getDefault()).format(date)

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = formattedDate)
                    Spacer(modifier = Modifier.width(16.dp))
                    IconButton(onClick = {
                        if (day.amount == 1) {
                            onEvent(DayEvents.DeleteDay(day))
                        } else {
                            val amount = day.amount - 1
                            onEvent(
                                DayEvents.UpdateDay(
                                    day.copy(
                                        amount = amount
                                    )
                                )
                            )
                        }
                    }) {
                        Icon(
                            painterResource(id = R.drawable.round_remove_circle_24),
                            contentDescription = "remove icon"
                        )
                    }
                    Text(text = day.amount.toString())
                    IconButton(onClick = {
                        val amount = day.amount + 1
                        onEvent(
                            DayEvents.UpdateDay(
                                day.copy(
                                    amount = amount
                                )
                            )
                        )
                    }) {
                        Icon(
                            painterResource(id = R.drawable.round_add_circle_24),
                            contentDescription = "add icon"
                        )
                    }
                }
            }
        }
    }
}