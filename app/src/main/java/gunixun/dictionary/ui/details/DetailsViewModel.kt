package gunixun.dictionary.ui.details

import androidx.lifecycle.LiveData
import gunixun.dictionary.domain.TranslationRepo
import gunixun.dictionary.ui.utils.AppState
import kotlinx.coroutines.*

class DetailsViewModel(
    private val translationRepo: TranslationRepo,
) : DetailsContract.DetailsViewModel() {
    override val data: LiveData<AppState> = _liveData

    override fun findWord(word: String) {
        _liveData.postValue(AppState.Loading)

        cancelJob()
        scope.launch { startFind(word) }
    }

    private suspend fun startFind(word: String) = withContext(Dispatchers.IO) {
        val res = translationRepo.getData(word)
        if (res.isNotEmpty()){
            _liveData.postValue(
                AppState.SuccessDataModel(
                    res[0]
                )
            )
        } else {
            _liveData.postValue(AppState.SuccessDataModel(null))
        }
    }

    override fun handleError(error: Throwable) {
        _liveData.postValue(AppState.Error(error))
    }
}