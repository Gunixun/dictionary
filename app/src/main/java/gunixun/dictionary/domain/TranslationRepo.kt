package gunixun.dictionary.domain

import gunixun.dictionary.domain.entities.DataModel

interface TranslationRepo {

    suspend fun getData(word: String): List<DataModel>

}