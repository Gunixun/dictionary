package gunixun.repository

import gunixun.model.DataModel
import gunixun.model.History

interface TranslationLocalRepo {

    suspend fun saveToDB(data: List<DataModel>)
    suspend fun getAll(): List<History>
}