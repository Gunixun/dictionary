package gunixun.dictionary.di

import gunixun.dictionary.data.mock.MockTranslationRepoImpl
import gunixun.dictionary.domain.TranslationRepo
import gunixun.dictionary.ui.translation.TranslationContract
import gunixun.dictionary.ui.translation.TranslationPresenter
import org.koin.dsl.module

val appModule = module {

    single<TranslationRepo> { MockTranslationRepoImpl() }
    single<TranslationContract.TranslationPresenterInterface> { TranslationPresenter(get()) }
}