package gunixun.dictionary.ui.translation

import androidx.lifecycle.LiveData
import gunixun.dictionary.domain.TranslationRepo
import gunixun.dictionary.ui.utils.AppState
import kotlinx.coroutines.*

class TranslationViewModel(
    private val translationRepo: TranslationRepo,
) : TranslationContract.TranslationViewModel() {
    override val data: LiveData<AppState> = _liveData

    override fun findWord(word: String) {
        _liveData.postValue(AppState.Loading)

        cancelJob()
        scope.launch { startFind(word) }
    }

    private suspend fun startFind(word: String) = withContext(Dispatchers.IO) {
        _liveData.postValue(AppState.Success(translationRepo.getData(word)))
    }

    override fun handleError(error: Throwable) {
        _liveData.postValue(AppState.Error(error))
    }
}