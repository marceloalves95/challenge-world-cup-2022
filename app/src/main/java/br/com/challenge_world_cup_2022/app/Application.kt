package br.com.challenge_world_cup_2022.app

import android.app.Application
import br.com.challenge_world_cup_2022.app.di.ChallengeWorldCupModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext

class Application : Application() {

    override fun onCreate() {
        super.onCreate()
        GlobalContext.startKoin {
            androidContext(this@Application)
        }
        ChallengeWorldCupModule.load()
    }
}