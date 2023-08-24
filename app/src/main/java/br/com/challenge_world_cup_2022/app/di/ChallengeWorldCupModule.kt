package br.com.challenge_world_cup_2022.app.di

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import br.com.challenge_world_cup_2022.data.local.DataSourceLocalImpl
import br.com.challenge_world_cup_2022.data.remote.BASE_URL
import br.com.challenge_world_cup_2022.data.remote.ChallengeWorldCupRepositoryImpl
import br.com.challenge_world_cup_2022.data.remote.services.ChallengeWorldCupServices
import br.com.challenge_world_cup_2022.data.remote.source.DataSourceRemoteImpl
import br.com.challenge_world_cup_2022.domain.repository.ChallengeWorldCupRepository
import br.com.challenge_world_cup_2022.domain.usecases.local.DisableNotificationUseCase
import br.com.challenge_world_cup_2022.domain.usecases.local.EnableNotificationUseCase
import br.com.challenge_world_cup_2022.domain.usecases.remote.GetAllMatchUseCase
import br.com.challenge_world_cup_2022.network.service.Service
import br.com.challenge_world_cup_2022.presentation.ui.MainViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.GlobalContext
import org.koin.core.module.Module
import org.koin.dsl.module

object ChallengeWorldCupModule {

    private const val PREFERENCES_NAME = "notifications_prefs"
    private val Context.dataStore by preferencesDataStore(name = PREFERENCES_NAME)

    fun load() {
        GlobalContext.loadKoinModules(
            listOf(
                dataModule(),
                domainModule(),
                presentationModule()
            )
        )
    }

    private fun dataModule(): Module = module {
        factory<ChallengeWorldCupServices> {
            Service.createService(
                baseUrl = BASE_URL
            )
        }
        single { DataSourceLocalImpl(dataStore = androidApplication().dataStore) }
        single { DataSourceRemoteImpl(service = get()) }
        single<ChallengeWorldCupRepository> {
            ChallengeWorldCupRepositoryImpl(
                dataSourceLocal = get(),
                dataSourceRemote = get()
            )
        }
    }

    private fun domainModule(): Module = module {
        factory {
            GetAllMatchUseCase(repository = get())
        }
        factory {
            EnableNotificationUseCase(repository = get())
        }
        factory {
            DisableNotificationUseCase(repository = get())
        }
    }

    private fun presentationModule(): Module = module {
        viewModel {
            MainViewModel(
                getAllMatchUseCase = get(),
                enableNotificationUseCase = get(),
                disableNotificationUseCase = get()
            )
        }
    }
}