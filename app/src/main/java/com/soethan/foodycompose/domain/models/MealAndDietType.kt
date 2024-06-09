package com.soethan.foodycompose.domain.models

data class MealAndDietType(
    val selectedMealType: String = "main course",
    val selectedDietType: String = "Gluten Free",
)


val DIET_TYPE_LIST = listOf(
    "Gluten Free",
    "Vegetarian",
    "Lacto-Vegetarian",
    "Ovo-Vegetarian",
    "Vegan",
    "Pescetarian",
    "Paleo",
    "Primal",
    "Low FODMAP",
    "Whole30"
)


val MEAL_TYPE_LIST = listOf(
    "main course",
    "side dish",
    "dessert",
    "appetizer",
    "salad",
    "bread",
    "breakfast",
    "soup",
    "beverage",
    "sauce",
    "marinade",
    "fingerfood",
    "snack",
    "drink"
)
