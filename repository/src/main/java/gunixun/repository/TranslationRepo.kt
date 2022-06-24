package gunixun.repository

import gunixun.model.DataModel

interface TranslationRepo {

    suspend fun getData(word: String): List<DataModel>

}