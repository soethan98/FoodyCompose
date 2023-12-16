package com.soethan.foodycompose.data.models

import kotlinx.serialization.Serializable


@Serializable()
data class IngredientDto(
    val aisle: String,
    val amount: Double,
    val consistency: String,
    val id: Int,
    val image: String,
    val name: String,
    val original: String,
    val originalName: String,
    val unit: String
)