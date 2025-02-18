package francisco.simon.cryptotracker.cryto.presentation.coin_list.components

import androidx.compose.runtime.Immutable
import francisco.simon.cryptotracker.cryto.presentation.models.CoinUi

@Immutable
data class CoinListState(
    val isLoading: Boolean = false,
    val coins: List<CoinUi> = emptyList(),
    val selectedCoin: CoinUi? = null
)