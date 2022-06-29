package gunixun.dictionary.di

import androidx.room.Room
import gunixun.details.*
import gunixun.history.HistoryContract
import gunixun.history.HistoryFragment
import gunixun.history.HistoryViewModel
import gunixun.repository.TranslationLocalRepo
import gunixun.repository.retrofit.SkyEngApi
import gunixun.repository.TranslationRepo
import gunixun.repository.TranslationRepoImpl
import gunixun.repository.retrofit.SkyEngTranslationRepoImpl
import gunixun.repository.room.HistoryDataBase
import gunixun.repository.room.RoomTranslationLocalRepoImpl
import gunixun.translation.TranslationContract
import gunixun.translation.TranslationFragment
import gunixun.translation.TranslationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val application = module {

    single<String>(named(API_URL)) { "https://dictionary.skyeng.ru/api/public/v1/" }

    single {
        Retrofit.Builder()
            .baseUrl(get<String>(named(API_URL)))
            .addConverterFactory(get())
            .build()
    }
    factory<Converter.Factory> { GsonConverterFactory.create() }

    single { get<Retrofit>().create(SkyEngApi::class.java) }
    single<TranslationRepo>(named(REMOTE_DB)) {
        SkyEngTranslationRepoImpl(
            api = get()
        )
    }

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

    scope<TranslationFragment> {
        viewModel<TranslationContract.TranslationViewModel> {
            TranslationViewModel(
                translationRepo = get(named(MERGE_DB))
            )
        }
    }
}

val detailsScreen = module {

    scope<DetailsFragment> {
        scoped { DetailsAdapter() }

        viewModel<DetailsContract.DetailsViewModel> {
            DetailsViewModel(
                translationRepo = get(named(REMOTE_DB))
            )
        }
    }
}


val historyScreen = module {

    scope<HistoryFragment> {
        viewModel<HistoryContract.HistoryViewModel> {
            HistoryViewModel(
                translationLocalRepo = get()
            )
        }
    }
}