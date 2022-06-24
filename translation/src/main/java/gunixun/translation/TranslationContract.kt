package gunixun.translation

import androidx.lifecycle.LiveData
import gunixun.core.view_model.BaseViewModel
import gunixun.utils.AppState

class TranslationContract {

    abstract class TranslationViewModel: BaseViewModel<AppState>() {
        abstract val data : LiveData<AppState>
        abstract  fun findWord(word: String)
    }
}