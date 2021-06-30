package kg.tutorialapp.forecast

import android.app.Application
import kg.tutorialapp.forecast.di.dataModule
import kg.tutorialapp.forecast.di.vmModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@App)
            modules (listOf(vmModule, dataModule))
        }
    }
}