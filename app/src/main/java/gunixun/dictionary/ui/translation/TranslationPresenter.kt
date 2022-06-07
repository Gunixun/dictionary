package gunixun.dictionary.ui.translation

import gunixun.dictionary.domain.TranslationRepo
import gunixun.dictionary.domain.entities.DataModel
import gunixun.dictionary.ui.utils.AppState
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers

class TranslationPresenter(
    private val translationRepo: TranslationRepo
) : TranslationContract.TranslationPresenterInterface {

    private var currentView: TranslationContract.TranslationViewInterface? = null
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onAttachView(view: TranslationContract.TranslationViewInterface) {
        currentView = view
    }

    override fun findWord(word: String) {
        currentView?.renderData(AppState.Loading)
        compositeDisposable.add(
            translationRepo
                .getData(word)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onSuccess = {
                        currentView?.renderData(AppState.Success(it))
                    },
                    onError = {
                        currentView?.renderData(AppState.Error(it))
                    }
                )
        )
    }

    override fun onDetachView(view: TranslationContract.TranslationViewInterface) {
        compositeDisposable.clear()
        if (view == currentView) {
            currentView = null
        }
    }
}