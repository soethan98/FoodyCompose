package com.soethan.foodycompose.data.remote

import com.soethan.foodycompose.BuildConfig
import com.soethan.foodycompose.data.models.FoodRecipe
import com.soethan.foodycompose.utils.Constants
import com.soethan.foodycompose.utils.Constants.RANDOM_RECIPES_URL
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE

import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.request.*
import io.ktor.http.*
import javax.inject.Inject

class SpoonacularClient @Inject constructor() {

    private val httpClient = HttpClient(Android) {
        defaultRequest {
            url(Constants.BASE_URL)
            header(HttpHeaders.ContentType, ContentType.Application.Json)

        }
        install(Logging) {
            logger = Logger.SIMPLE
        }

        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }


    suspend fun getRandomRecipes(): FoodRecipe {
        return httpClient.get(RANDOM_RECIPES_URL) {
            parameter("apiKey", BuildConfig.API_KEY)
        }.body()

    }

}