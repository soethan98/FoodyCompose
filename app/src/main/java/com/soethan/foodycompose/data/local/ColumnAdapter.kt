package com.soethan.foodycompose.data.local

import app.cash.sqldelight.ColumnAdapter
import com.soethan.foodycompose.data.models.IngredientDto
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

val ingredientsAdapter = object : ColumnAdapter<List<IngredientDto>,String>{
    override fun decode(databaseValue: String): List<IngredientDto> {
        return  Json.decodeFromString(databaseValue)
    }

    override fun encode(value: List<IngredientDto>): String {
        return Json.encodeToString(value)
    }

}