package gunixun.dictionary.ui.translation

import androidx.lifecycle.MutableLiveData
import gunixun.dictionary.domain.TranslationRepo
import gunixun.dictionary.ui.utils.AppState
import kotlinx.coroutines.*

class TranslationViewModel(
    private val translationRepo: TranslationRepo,
    private val scope: CoroutineScope,
) : TranslationContract.TranslationViewModel() {
    override val data: MutableLiveData<AppState> = MutableLiveData<AppState>()

    private var job: Job? = null

    override fun findWord(word: String) {
        data.postValue(AppState.Loading)

        if (job?.isActive == true) {
            job?.cancel()
        }
        job = scope.launch() {
            try {
                withContext(Dispatchers.IO) {
                    translationRepo.getData(word).let { result ->
                        data.postValue(AppState.Success(result))
                    }
                }
            } catch (e: Throwable) {
                data.postValue(AppState.Error(e))
            }
        }
    }

    override fun onCleared() {
        scope.cancel()
        super.onCleared()
    }
}