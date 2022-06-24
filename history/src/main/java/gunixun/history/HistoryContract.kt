package gunixun.history

import androidx.lifecycle.LiveData
import gunixun.core.view_model.BaseViewModel
import gunixun.utils.AppState

class HistoryContract {

    abstract class HistoryViewModel: BaseViewModel<AppState>() {
        abstract val data : LiveData<AppState>
        abstract  fun getAll()
    }
}