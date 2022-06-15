package gunixun.dictionary.data.mock

import gunixun.dictionary.domain.TranslationRepo
import gunixun.dictionary.domain.entities.DataModel
import gunixun.dictionary.domain.entities.Meaning

class MockTranslationRepoImpl : TranslationRepo {


    private val data: List<DataModel> = listOf(
        DataModel(id = 480469417, text = "help", meanings = listOf(
            Meaning(180270, "помогать", "кому-либо c чем-либо; делать что-либо", "//cdn-user77752.skyeng.ru/resized-images/96x72/jpeg/60/8d5a91716bd730c3d4a8bdeb7238a3b8.jpeg",  "//cdn-user77752.skyeng.ru/resized-images/640x480/jpeg/60/8d5a91716bd730c3d4a8bdeb7238a3b8.jpeg"),
            Meaning(180272, "способствовать", "", "",  ""),
            Meaning(180272, "улучшать состояние", "кого-либо или чего-либо", "",  ""),
        )),
        DataModel(id = 480469417, text = "test", meanings = listOf(
            Meaning(180272, "экзамен", "", "",  ""),
            Meaning(180272, "тест", "", "",  ""),
            Meaning(180272, "анализ", "", "",  ""),
        )),
        DataModel(id = 480469417, text = "post", meanings = listOf(
            Meaning(180272, "караул", "", "",  ""),
            Meaning(180272, "должность", "", "",  ""),
            Meaning(180272, "гарнизон", "", "",  ""),
        ))
    )

    override suspend fun getData(word: String) = data
}