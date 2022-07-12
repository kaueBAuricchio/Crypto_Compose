package com.example.crypto_compose.presentation.coin_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crypto_compose.commom.Constants
import com.example.crypto_compose.commom.Resource
import com.example.crypto_compose.domain.use_case.get_coin.GetCoinUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class CoinDetailViewModel @Inject constructor(
    private val getCoinUseCase: GetCoinUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _coinState = mutableStateOf(CoinDetailState())
    val state : State<CoinDetailState> = _coinState

    init {
        savedStateHandle.get<String>(Constants.PARAM_COIN_ID)?.let { coinId ->
            getCoin(coinId)
        }
    }

    private fun getCoin(coinId: String){
        getCoinUseCase(coinId).onEach { result ->
            when(result){
                is Resource.Success ->{
                    _coinState.value = CoinDetailState(coin = result.data)
                }

                is Resource.Error ->{
                    _coinState.value = CoinDetailState(error = result.message ?: "An unexpected error occured")
                }

                is Resource.Loading ->{
                    _coinState.value = CoinDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}