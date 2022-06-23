package gunixun.dictionary.data

import gunixun.dictionary.domain.TranslationLocalRepo
import gunixun.dictionary.domain.TranslationRepo
import gunixun.dictionary.domain.entities.DataModel

class TranslationRepoImpl(
    private val repositoryRemote: TranslationRepo,
    private val repositoryLocal: TranslationLocalRepo
) : TranslationRepo {

    override suspend fun getData(word: String): List<DataModel> {
        val data = repositoryRemote.getData(word)
        repositoryLocal.saveToDB(data)
        return data
    }
}