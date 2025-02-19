package francisco.simon.cryptotracker.cryto.presentation.coin_list

import francisco.simon.cryptotracker.core.domain.util.NetworkError

sealed interface CoinListEvent {
    data class Error(val error: NetworkError) : CoinListEvent
}