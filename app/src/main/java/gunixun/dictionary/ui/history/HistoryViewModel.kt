package gunixun.dictionary.ui.history

import androidx.lifecycle.LiveData
import gunixun.dictionary.domain.TranslationLocalRepo
import gunixun.dictionary.ui.utils.AppState
import kotlinx.coroutines.*

class HistoryViewModel(
    private val translationLocalRepo: TranslationLocalRepo,
) : HistoryContract.HistoryViewModel() {
    override val data: LiveData<AppState> = _liveData

    override fun getAll() {
        _liveData.postValue(AppState.Loading)

        cancelJob()
        scope.launch { startGetAll() }
    }

    private suspend fun startGetAll() = withContext(Dispatchers.IO) {
        _liveData.postValue(AppState.SuccessHistory(translationLocalRepo.getAll()))
    }

    override fun handleError(error: Throwable) {
        _liveData.postValue(AppState.Error(error))
    }
}