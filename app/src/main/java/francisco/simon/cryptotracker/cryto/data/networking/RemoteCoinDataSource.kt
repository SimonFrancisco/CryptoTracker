package francisco.simon.cryptotracker.cryto.data.networking

import francisco.simon.cryptotracker.core.data.networking.constructUrl
import francisco.simon.cryptotracker.core.data.networking.safeCall
import francisco.simon.cryptotracker.core.domain.util.NetworkError
import francisco.simon.cryptotracker.core.domain.util.Result
import francisco.simon.cryptotracker.core.domain.util.map
import francisco.simon.cryptotracker.cryto.data.mappers.toCoin
import francisco.simon.cryptotracker.cryto.data.networking.dto.CoinsResponseDto
import francisco.simon.cryptotracker.cryto.domain.Coin
import francisco.simon.cryptotracker.cryto.domain.CoinDataSource
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class RemoteCoinDataSource(
    private val httpClient: HttpClient
) : CoinDataSource {
    override suspend fun getCoins(): Result<List<Coin>, NetworkError> {
        return safeCall<CoinsResponseDto> {
            httpClient.get(
                urlString = constructUrl("assets"),
            )
        }.map { response ->
            response.data.map {
                it.toCoin()
            }
        }
    }
}
