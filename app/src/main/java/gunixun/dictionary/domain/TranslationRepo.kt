package gunixun.dictionary.domain

import gunixun.dictionary.domain.entities.DataModel
import io.reactivex.rxjava3.core.Single

interface TranslationRepo {

    fun getData(word: String): Single<List<DataModel>>

}