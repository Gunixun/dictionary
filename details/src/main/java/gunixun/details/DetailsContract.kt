package gunixun.details

import androidx.lifecycle.LiveData
import gunixun.core.view_model.BaseViewModel
import gunixun.utils.AppState

class DetailsContract {

    abstract class DetailsViewModel: BaseViewModel<AppState>() {
        abstract val data : LiveData<AppState>
        abstract  fun findWord(word: String)
    }
}