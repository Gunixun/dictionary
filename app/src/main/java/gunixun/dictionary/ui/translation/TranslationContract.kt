package gunixun.dictionary.ui.translation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import gunixun.dictionary.ui.BaseViewModel
import gunixun.dictionary.ui.utils.AppState

class TranslationContract {

    abstract class TranslationViewModel: BaseViewModel<AppState>() {
        abstract val data : LiveData<AppState>
        abstract  fun findWord(word: String)
    }
}