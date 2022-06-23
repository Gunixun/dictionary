package gunixun.dictionary.ui.utils

import gunixun.dictionary.domain.entities.DataModel
import gunixun.dictionary.domain.entities.History

sealed class AppState {
    data class Success(val data: List<DataModel>) : AppState()
    data class SuccessDataModel(val data: DataModel?) : AppState()
    data class SuccessHistory(val data: List<History>) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}