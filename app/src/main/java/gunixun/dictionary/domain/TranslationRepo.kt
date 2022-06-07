package gunixun.dictionary.domain

import io.reactivex.rxjava3.core.Single

interface TranslationRepo<T> {

    fun getData(word: String): Single<T>

}