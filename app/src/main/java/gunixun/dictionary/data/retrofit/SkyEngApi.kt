package gunixun.dictionary.data.retrofit

import gunixun.dictionary.data.retrofit.dto.DataModelDto
import retrofit2.http.GET
import retrofit2.http.Query


interface SkyEngApi {

    @GET("words/search")
    suspend fun getData(
        @Query("search") search: String
    ): List<DataModelDto>
}