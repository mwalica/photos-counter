package ch.walica.photo_counter_1

sealed interface DayEvents {

    data object InsertDay: DayEvents
    data object UpdateDay: DayEvents
    data object ShowDialog: DayEvents
    data object HideDialog: DayEvents
    data object DeleteDay: DayEvents
}