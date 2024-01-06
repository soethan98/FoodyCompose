package com.soethan.foodycompose.data.mapper

import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.ktor.statusCode
import com.skydoves.sandwich.mappers.ApiErrorModelMapper
import com.skydoves.sandwich.message
import com.soethan.foodycompose.data.models.RecipeErrorResponse

object ErrorResponseMapper : ApiErrorModelMapper<RecipeErrorResponse> {

    override fun map(apiErrorResponse: ApiResponse.Failure.Error): RecipeErrorResponse {
        return RecipeErrorResponse(
            apiErrorResponse.statusCode.code,
            apiErrorResponse.message()
        )
    }
}