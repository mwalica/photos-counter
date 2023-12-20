package ch.walica.photo_counter_1.views

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.ExitToApp
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext

import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import ch.walica.photo_counter_1.DayEvents
import ch.walica.photo_counter_1.DayState

import ch.walica.photo_counter_1.components.DeleteAlert

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
    val activity = LocalContext.current as Activity
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Photos counter") },
                actions = {
                    IconButton(onClick = { activity.finish() }) {
                        Icon(
                            imageVector = Icons.Rounded.ExitToApp,
                            contentDescription = "close app"
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { onEvent(DayEvents.InsertDay) }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "add day")
            }
        }
    ) { padding ->

        if (state.isShowAlert) {
            DeleteAlert(state = state, onEvent = onEvent)
        }
        LazyColumn(
            contentPadding = padding,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                Text(text = buildAnnotatedString {
                    append("Processed photos: ")
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    ) {
                        append(state.days.sumOf { it.amount }.toString())
                    }
                })
            }

            items(state.days) { day ->
                val date =
                    ZonedDateTime.ofInstant(Instant.ofEpochSecond(day.date), ZoneId.systemDefault())
                val formattedDate =
                    DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.getDefault()).format(date)

                Column {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp, vertical = 12.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(16.dp)
                                .clip(CircleShape)
                                .background(
                                    MaterialTheme.colorScheme.tertiary.copy(
                                        alpha = when (day.amount) {
                                            1 -> 0.3f
                                            2 -> 0.4f
                                            in 3..4 -> 0.8f
                                            else -> 1f
                                        }
                                    )
                                )
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = formattedDate)
                        Spacer(modifier = Modifier.weight(1f))
                        FilledIconButton(
                            colors = IconButtonDefaults.iconButtonColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                            ),
                            modifier = Modifier.size(28.dp),
                            onClick = {
                                if (day.amount == 1) {
                                    onEvent(DayEvents.ShowDialog(day))
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
                                imageVector = Icons.Rounded.KeyboardArrowLeft,
                                contentDescription = "remove icon",
                            )
                        }
                        Text(
                            text = day.amount.toString(),
                            style = MaterialTheme.typography.titleLarge,
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                        FilledTonalIconButton(
                            colors = IconButtonDefaults.iconButtonColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                            ),
                            modifier = Modifier.size(28.dp),
                            onClick = {
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
                                imageVector = Icons.Rounded.KeyboardArrowRight,
                                contentDescription = "add icon",

                                )
                        }
                    }
                    Divider()
                }
            }
        }
    }
}