package francisco.simon.cryptotracker.cryto.data.mappers

import francisco.simon.cryptotracker.cryto.data.networking.dto.CoinDto
import francisco.simon.cryptotracker.cryto.domain.Coin

fun CoinDto.toCoin(): Coin {
    return Coin(
        id = id,
        rank = rank,
        name = name,
        symbol = symbol,
        marketCapUsd = marketCapUsd,
        priceUsd = priceUsd,
        changePercent24Hr = changePercent24Hr
    )
}