package gunixun.dictionary.di

import gunixun.dictionary.data.retrofit.SkyEngApi
import gunixun.dictionary.data.retrofit.SkyEngTranslationRepoImpl
import gunixun.dictionary.domain.TranslationRepo
import gunixun.dictionary.ui.translation.TranslationContract
import org.koin.androidx.viewmodel.dsl.viewModel
import gunixun.dictionary.ui.translation.TranslationViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

const val apiUrl = "api_url"

val appModule = module {

    single<String>(named(apiUrl)) { "https://dictionary.skyeng.ru/api/public/v1/" }

    single {
        Retrofit.Builder()
            .baseUrl(get<String>(named(apiUrl)))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(get())
            .build()
    }
    factory<Converter.Factory> { GsonConverterFactory.create() }

    single<SkyEngApi> { get<Retrofit>().create(SkyEngApi::class.java) }

    single<TranslationRepo> { SkyEngTranslationRepoImpl(api = get()) }

    viewModel<TranslationContract.TranslationViewModel> {
        TranslationViewModel(
            translationRepo = get(),
            scope = CoroutineScope(Dispatchers.Default)
        )
    }
}