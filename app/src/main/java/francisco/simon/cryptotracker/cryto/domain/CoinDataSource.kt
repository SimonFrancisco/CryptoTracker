package francisco.simon.cryptotracker.cryto.domain

import francisco.simon.cryptotracker.core.domain.util.NetworkError
import francisco.simon.cryptotracker.core.domain.util.Result

interface CoinDataSource {
    suspend fun getCoins(): Result<List<Coin>, NetworkError>
}