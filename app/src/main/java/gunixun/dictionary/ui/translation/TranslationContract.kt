package gunixun.dictionary.ui.translation

import gunixun.dictionary.ui.utils.AppState

class TranslationContract {

    interface TranslationViewInterface {
        fun renderData(appState: AppState)
    }

    interface TranslationPresenterInterface {
        fun onAttachView(view: TranslationViewInterface)
        fun onDetachView(view: TranslationViewInterface)
        fun findWord(word: String)
    }
}