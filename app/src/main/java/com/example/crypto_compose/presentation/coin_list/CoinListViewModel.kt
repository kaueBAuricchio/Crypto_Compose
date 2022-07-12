package com.example.crypto_compose.presentation.coin_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crypto_compose.commom.Resource
import com.example.crypto_compose.domain.use_case.get_coins.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase
) : ViewModel() {

    private val _coinState = mutableStateOf(CoinListState())
    val state : State<CoinListState> = _coinState

    init {
        getCoins()
    }

    private fun getCoins(){
        getCoinsUseCase().onEach { result ->
            when(result){
                is Resource.Success ->{
                    _coinState.value = CoinListState(listCoins = result.data ?: emptyList())
                }

                is Resource.Error ->{
                    _coinState.value = CoinListState(error = result.message ?: "An unexpected error occured")
                }

                is Resource.Loading ->{
                    _coinState.value = CoinListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}