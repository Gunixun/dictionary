package gunixun.utils

import gunixun.model.DataModel
import gunixun.model.History

sealed class AppState {
    data class Success(val data: List<DataModel>) : AppState()
    data class SuccessDataModel(val data: DataModel?) : AppState()
    data class SuccessHistory(val data: List<History>) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}