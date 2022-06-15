package gunixun.dictionary.data.retrofit

import gunixun.dictionary.domain.TranslationRepo
import gunixun.dictionary.domain.entities.DataModel

class SkyEngTranslationRepoImpl(
    private val api: SkyEngApi
) : TranslationRepo{

    override suspend fun getData(word: String): List<DataModel> {
        return convertReposDtoToEntity(api.getData(word))
    }
}