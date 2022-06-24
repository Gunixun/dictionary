package gunixun.repository.retrofit

import gunixun.model.DataModel
import gunixun.model.Meaning
import gunixun.repository.retrofit.dto.DataModelDto

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
                    imageUrl = meaning.imageUrl,
                    transcription = meaning.transcription
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