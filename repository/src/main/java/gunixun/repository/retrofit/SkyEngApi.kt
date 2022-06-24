package gunixun.repository.retrofit

import gunixun.repository.retrofit.dto.DataModelDto
import retrofit2.http.GET
import retrofit2.http.Query


interface SkyEngApi {

    @GET("words/search")
    suspend fun getData(
        @Query("search") search: String
    ): List<DataModelDto>
}