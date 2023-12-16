package ch.walica.photo_counter_1

import ch.walica.photo_counter_1.database.Day

data class DayState(
    val days: List<Day> = emptyList(),
    val amount: Int = 0,
    val date: Long = 0,
    val isShowAlert: Boolean = false
)