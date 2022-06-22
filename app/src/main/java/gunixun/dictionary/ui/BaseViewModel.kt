package gunixun.dictionary.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*

abstract class BaseViewModel<T>(
    protected val _liveData: MutableLiveData<T> = MutableLiveData()
): ViewModel() {

    protected val scope = CoroutineScope(
        Dispatchers.Main
                + CoroutineExceptionHandler { _, throwable ->
            handleError(throwable)
        })

    abstract fun handleError(error: Throwable)

    protected fun cancelJob() {
        scope.coroutineContext.cancelChildren()
    }

    override fun onCleared() {
        super.onCleared()
        cancelJob()
    }
}