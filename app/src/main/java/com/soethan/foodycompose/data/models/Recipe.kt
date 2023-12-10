package com.soethan.foodycompose.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecipeDto(
    val id: Int,
    val title: String,
    val image: String
)