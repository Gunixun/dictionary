package gunixun.dictionary

import android.app.Application
import gunixun.dictionary.di.application
import gunixun.dictionary.di.detailsScreen
import gunixun.dictionary.di.historyScreen
import gunixun.dictionary.di.mainScreen
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class App : Application() {


    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(listOf(application, mainScreen, historyScreen, detailsScreen))
        }
    }
}