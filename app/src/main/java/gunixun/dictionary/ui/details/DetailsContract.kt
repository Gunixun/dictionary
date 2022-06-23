package gunixun.dictionary.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import gunixun.dictionary.ui.BaseViewModel
import gunixun.dictionary.ui.utils.AppState

class DetailsContract {

    abstract class DetailsViewModel: BaseViewModel<AppState>() {
        abstract val data : LiveData<AppState>
        abstract  fun findWord(word: String)
    }
}