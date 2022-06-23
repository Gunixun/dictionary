package gunixun.dictionary.ui.history

import androidx.lifecycle.LiveData
import gunixun.dictionary.ui.BaseViewModel
import gunixun.dictionary.ui.utils.AppState

class HistoryContract {

    abstract class HistoryViewModel: BaseViewModel<AppState>() {
        abstract val data : LiveData<AppState>
        abstract  fun getAll()
    }
}