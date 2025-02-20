package francisco.simon.cryptotracker.cryto.presentation.coin_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import francisco.simon.cryptotracker.core.domain.util.onError
import francisco.simon.cryptotracker.core.domain.util.onSuccess
import francisco.simon.cryptotracker.cryto.domain.CoinDataSource
import francisco.simon.cryptotracker.cryto.presentation.coin_list.components.CoinListState
import francisco.simon.cryptotracker.cryto.presentation.models.toCoinUi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CoinListViewModel(
    private val coinDataSource: CoinDataSource
) : ViewModel() {

    private val _state = MutableStateFlow(CoinListState())
    val state = _state.onStart {
        loadCoins()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = CoinListState()
    )
    private val _events = Channel<CoinListEvent>()
    val events = _events.receiveAsFlow()

    fun onAction(action: CoinListAction) {
        when (action) {
            is CoinListAction.OnCoinClick -> {
                _state.update {
                    it.copy(selectedCoin = action.coinUi)
                }
            }
        }
    }

    private fun loadCoins() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true
                )
            }
            coinDataSource.getCoins()
                .onSuccess { coins ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            coins = coins.map { coin ->
                                coin.toCoinUi()
                            }
                        )
                    }
                }
                .onError { error ->
                    _state.update {
                        it.copy(isLoading = false)
                    }
                    _events.send(CoinListEvent.Error(error))

                }
        }
    }
}

