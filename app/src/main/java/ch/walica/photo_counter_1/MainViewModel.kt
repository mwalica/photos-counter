package ch.walica.photo_counter_1


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ch.walica.photo_counter_1.database.Day
import ch.walica.photo_counter_1.database.DaysDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.ZonedDateTime

class MainViewModel(private val dao: DaysDao) : ViewModel() {

    private val _days =
        dao.getDays().stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    private val _state = MutableStateFlow(DayState())
    val state = combine(_state, _days) { state, days ->
        state.copy(
            days = days,
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), DayState())

    fun onEvent(event: DayEvents) {
        when (event) {
            is DayEvents.InsertDay -> {
                val date = ZonedDateTime.now()
                viewModelScope.launch {
                    dao.upsertDay(
                        Day(
                            date = date.toEpochSecond(),
                            year = date.year
                        )
                    )
                }
            }

            is DayEvents.UpdateDay -> {
                viewModelScope.launch {
                    dao.upsertDay(event.day)
                }
            }

            is DayEvents.DeleteDay -> {
                viewModelScope.launch {
                    dao.deleteDay(event.day)
                }
            }

            is DayEvents.ShowDialog -> {
                _state.update { state ->
                    state.copy(
                        isShowAlert = true
                    )
                }
            }

            is DayEvents.HideDialog -> {
                _state.update { state ->
                    state.copy(
                        isShowAlert = false
                    )
                }
            }
        }
    }


}