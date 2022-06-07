package gunixun.dictionary.ui.utils

import gunixun.dictionary.domain.entities.DataModel

sealed class AppState {
    data class Success(val data: List<DataModel>) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}