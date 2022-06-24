package gunixun.repository.retrofit

import gunixun.repository.TranslationRepo

class SkyEngTranslationRepoImpl(
    private val api: SkyEngApi
) : TranslationRepo {

    override suspend fun getData(word: String): List<gunixun.model.DataModel> {
        return convertReposDtoToEntity(api.getData(word))
    }
}