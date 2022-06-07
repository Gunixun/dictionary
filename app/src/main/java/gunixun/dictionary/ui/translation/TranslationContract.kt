package gunixun.dictionary.ui.translation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import gunixun.dictionary.ui.utils.AppState

class TranslationContract {

    abstract class TranslationViewModel: ViewModel() {
        abstract val data : LiveData<AppState>
        abstract  fun findWord(word: String)
    }
}