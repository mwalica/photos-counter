package ch.walica.photo_counter_1

import ch.walica.photo_counter_1.database.Day

data class DayState(
    val days: List<Day> = emptyList(),
    val years: Set<Int> = emptySet(),
    val amount: Int = 0,
    val date: Long = 0,
    val isShowAlert: Boolean = false,
    val selectedDay: Day? = null
)