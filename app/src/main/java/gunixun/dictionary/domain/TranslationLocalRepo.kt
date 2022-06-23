package gunixun.dictionary.domain

import gunixun.dictionary.domain.entities.DataModel
import gunixun.dictionary.domain.entities.History

interface TranslationLocalRepo {

    suspend fun saveToDB(data: List<DataModel>)
    suspend fun getAll(): List<History>
}