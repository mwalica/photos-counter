package ch.walica.photo_counter_1

import ch.walica.photo_counter_1.database.Day

sealed interface DayEvents {

    data object InsertDay : DayEvents
    data class UpdateDay(val day: Day) : DayEvents
    data class ShowDialog(val day: Day) : DayEvents
    data object HideDialog : DayEvents
    data class DeleteDay(val day: Day) : DayEvents
}