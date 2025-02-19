package francisco.simon.cryptotracker.di

import francisco.simon.cryptotracker.core.data.networking.HttpClientFactory
import francisco.simon.cryptotracker.cryto.data.networking.RemoteCoinDataSource
import francisco.simon.cryptotracker.cryto.domain.CoinDataSource
import francisco.simon.cryptotracker.cryto.presentation.coin_list.CoinListViewModel
import io.ktor.client.engine.cio.CIO
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    single { HttpClientFactory.create(CIO.create()) }
    singleOf(::RemoteCoinDataSource).bind<CoinDataSource>()

    viewModelOf(::CoinListViewModel)
}