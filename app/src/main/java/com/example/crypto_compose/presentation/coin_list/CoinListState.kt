package com.example.crypto_compose.presentation.coin_list

import com.example.crypto_compose.domain.model.Coin

data class CoinListState(
    val isLoading : Boolean = false,
    val listCoins : List<Coin> = emptyList(),
    val error : String = ""
) {
}