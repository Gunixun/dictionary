package gunixun.dictionary.data.retrofit

import gunixun.dictionary.domain.TranslationRepo
import gunixun.dictionary.domain.entities.DataModel
import io.reactivex.rxjava3.core.Single

class SkyEngTranslationRepoImpl(
    private val api: SkyEngApi
) : TranslationRepo{

    override fun getData(word: String): Single<List<DataModel>> {
        return api.getData(word).map { convertReposDtoToEntity(it) }
    }
}