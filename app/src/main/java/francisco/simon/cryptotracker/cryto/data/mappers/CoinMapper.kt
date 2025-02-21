package francisco.simon.cryptotracker.cryto.data.mappers

import francisco.simon.cryptotracker.cryto.data.networking.dto.CoinDto
import francisco.simon.cryptotracker.cryto.data.networking.dto.CoinPriceDto
import francisco.simon.cryptotracker.cryto.domain.Coin
import francisco.simon.cryptotracker.cryto.domain.CoinPrice
import java.time.Instant
import java.time.ZoneId

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

fun CoinPriceDto.toCoinPrice(): CoinPrice {

    return CoinPrice(
        priceUsd = priceUsd,
        dateTime = Instant.ofEpochMilli(dateTime).atZone(ZoneId.systemDefault())
    )
}