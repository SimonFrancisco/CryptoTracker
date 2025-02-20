package francisco.simon.cryptotracker.cryto.data.networking.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CoinPriceDto(
    @SerialName("priceUsd")
    val priceUsd: Double,
    @SerialName("time")
    val dateTime: Long
)