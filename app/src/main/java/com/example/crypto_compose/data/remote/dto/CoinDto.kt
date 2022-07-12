package com.example.crypto_compose.data.remote.dto

import com.example.crypto_compose.domain.model.Coin
import com.squareup.moshi.Json

data class CoinDto(
    val id: String,
    @Json(name = "is_active")
    val is_active: Boolean,
    @Json(name = "is_new")
    val is_new: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String,
    val type: String
)

fun CoinDto.toCoin() : Coin{
    return Coin(
        id = id,
        is_active = is_active,
        name = name,
        rank = rank,
        symbol = symbol
    )
}