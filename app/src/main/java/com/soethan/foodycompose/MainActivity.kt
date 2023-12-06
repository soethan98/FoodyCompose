package com.soethan.foodycompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.soethan.foodycompose.data.models.FoodRecipe
import com.soethan.foodycompose.data.remote.SpoonacularClient
import com.soethan.foodycompose.ui.theme.FoodyComposeTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val client = SpoonacularClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var scope = rememberCoroutineScope()
            var recipes by remember {
                mutableStateOf<FoodRecipe?>(null)
            }

            LaunchedEffect(key1 = Unit) {
                scope.launch {
                    recipes = client.getRandomRecipes()
                }
            }
            FoodyComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting(recipes?.recipes?.first()?.title ?: "Null recipe")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FoodyComposeTheme {
        Greeting("Android")
    }
}