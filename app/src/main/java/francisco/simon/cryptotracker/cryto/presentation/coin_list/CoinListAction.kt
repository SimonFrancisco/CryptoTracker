package francisco.simon.cryptotracker.cryto.presentation.coin_list

import francisco.simon.cryptotracker.cryto.presentation.models.CoinUi

sealed interface CoinListAction {
    data class OnCoinClick(val coinUi: CoinUi) : CoinListAction
}