package gunixun.dictionary.data.retrofit

import gunixun.dictionary.data.retrofit.dto.DataModelDto
import gunixun.dictionary.domain.entities.DataModel
import gunixun.dictionary.domain.entities.Meaning

fun convertReposDtoToEntity(dataModels: List<DataModelDto>): List<DataModel> {
    val res: MutableList<DataModel> = mutableListOf()

    for (dataModel in dataModels) {
        val meanings: MutableList<Meaning> = mutableListOf()
        for (meaning in dataModel.meanings) {
            meanings.add(
                Meaning(
                    id = meaning.id,
                    translationText = meaning.translation.text ?: "",
                    translationNote = meaning.translation.note ?: "",
                    previewUrl = meaning.previewUrl,
                    imageUrl = meaning.imageUrl
                )
            )
        }
        res.add(
            DataModel(
                id = dataModel.id,
                text = dataModel.text,
                meanings = meanings
            )
        )
    }
    return res
}