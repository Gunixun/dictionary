package gunixun.dictionary.di

import androidx.room.Room
import gunixun.dictionary.data.TranslationRepoImpl
import gunixun.dictionary.data.retrofit.SkyEngApi
import gunixun.dictionary.data.retrofit.SkyEngTranslationRepoImpl
import gunixun.dictionary.data.room.HistoryDataBase
import gunixun.dictionary.data.room.RoomTranslationLocalRepoImpl
import gunixun.dictionary.data.room.dao.HistoryDao
import gunixun.dictionary.domain.TranslationLocalRepo
import gunixun.dictionary.domain.TranslationRepo
import gunixun.dictionary.ui.details.DetailsContract
import gunixun.dictionary.ui.details.DetailsViewModel
import gunixun.dictionary.ui.history.HistoryContract
import gunixun.dictionary.ui.history.HistoryViewModel
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



val application = module {

    single<String>(named(API_URL)) { "https://dictionary.skyeng.ru/api/public/v1/" }

    single {
        Retrofit.Builder()
            .baseUrl(get<String>(named(API_URL)))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(get())
            .build()
    }
    factory<Converter.Factory> { GsonConverterFactory.create() }

    single { get<Retrofit>().create(SkyEngApi::class.java) }
    single<TranslationRepo>(named(REMOTE_DB)) { SkyEngTranslationRepoImpl(api = get()) }

    single { Room.databaseBuilder(get(), HistoryDataBase::class.java, "HistoryDB").build() }
    single { get<HistoryDataBase>().historyDao() }
    single<TranslationLocalRepo>() {
        RoomTranslationLocalRepoImpl(
            historyDao = get()
        )
    }

    single<TranslationRepo>(named(MERGE_DB)) {
        TranslationRepoImpl(
            repositoryRemote = get(named(REMOTE_DB)),
            repositoryLocal = get()
        )
    }
}

val mainScreen = module {

    viewModel<TranslationContract.TranslationViewModel> {
        TranslationViewModel(
            translationRepo = get(named(MERGE_DB))
        )
    }
}

val detailsScreen = module {

    viewModel<DetailsContract.DetailsViewModel> {
        DetailsViewModel(
            translationRepo = get(named(REMOTE_DB))
        )
    }
}


val historyScreen = module {

    viewModel<HistoryContract.HistoryViewModel> {
        HistoryViewModel(
            translationLocalRepo = get()
        )
    }
}