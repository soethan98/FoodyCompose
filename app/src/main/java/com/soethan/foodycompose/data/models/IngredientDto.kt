package com.soethan.foodycompose.data.models

import com.soethan.foodycompose.domain.models.IngredientEntity
import kotlinx.serialization.Serializable


@Serializable()
data class IngredientDto(
    val aisle: String?,
    val amount: Double,
    val consistency: String?,
    val id: Int,
    val image: String?,
    val name: String?,
    val original: String?,
    val originalName: String?,
    val unit: String?
)


fun IngredientDto.toIngredientEntity():IngredientEntity{
    return IngredientEntity(
        aisle = this.aisle ?: "",
        amount = this.amount ?: 0.0,
        consistency = this.consistency ?: "",
        id = this.id,
        name = this.name ?: "",
        image = this.image ?: "",
        originalName = this.originalName ?: "",
        original = this.original ?: "",
        unit = this.unit ?: ""
    )
}