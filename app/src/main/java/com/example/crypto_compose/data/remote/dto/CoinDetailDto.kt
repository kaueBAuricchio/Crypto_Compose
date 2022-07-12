package com.example.crypto_compose.data.remote.dto

import com.example.crypto_compose.domain.model.CoinDetail
import com.squareup.moshi.Json

data class CoinDetailDto(
    val description: String,
    @Json(name = "development_status")
    val development_status: String,
    @Json(name = "first_data_at")
    val first_data_at: String,
    @Json(name = "hardware_wallet")
    val hardware_wallet: Boolean,
    @Json(name = "hash_algorithm")
    val hash_algorithm: String,
    val id: String,
    @Json(name = "is_active")
    val is_active: Boolean,
    @Json(name = "is_new")
    val is_new: Boolean,
    @Json(name = "last_data_at")
    val last_data_at: String,
    val links: Links,
    @Json(name = "links_extended")
    val links_extended: List<LinksExtended>,
    val message: String,
    val name: String,
    @Json(name = "open_source")
    val open_source: Boolean,
    @Json(name = "org_structure")
    val org_structure: String,
    @Json(name = "proof_type")
    val proof_type: String,
    val rank: Int,
    @Json(name = "started_at")
    val started_at: String,
    val symbol: String,
    val tags: List<Tag>,
    val team: List<TeamMember>,
    val type: String,
    val whitepaper: Whitepaper
)

fun CoinDetailDto.toCoinDetail(): CoinDetail{
    return CoinDetail(
        coinId = id,
        name = name,
        description = description,
        symbol = symbol,
        rank = rank,
        isActive = is_active,
        tags = tags.map { it.name },
        teamMember = team
    )
}