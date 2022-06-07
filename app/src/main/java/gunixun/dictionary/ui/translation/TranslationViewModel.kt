package gunixun.dictionary.ui.translation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import gunixun.dictionary.domain.TranslationRepo
import gunixun.dictionary.ui.utils.AppState
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers

class TranslationViewModel(
    private val translationRepo: TranslationRepo
): TranslationContract.TranslationViewModel() {
    override val data: MutableLiveData<AppState> = MutableLiveData<AppState>()

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun findWord(word: String) {
        data.postValue(AppState.Loading)
        compositeDisposable.add(
            translationRepo
                .getData(word)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onSuccess = {
                        data.postValue(AppState.Success(it))
                    },
                    onError = {
                        data.postValue(AppState.Error(it))
                    }
                )
        )
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}