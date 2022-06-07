package gunixun.dictionary.data.retrofit

import gunixun.dictionary.data.retrofit.dto.DataModelDto
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query


interface SkyEngApi {

    @GET("words/search")
    fun getData(
        @Query("search") search: String
    ): Single<List<DataModelDto>>
}