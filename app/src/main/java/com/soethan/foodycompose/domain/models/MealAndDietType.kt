package com.soethan.foodycompose.domain.models

data class MealAndDietType(
    val selectedMealType: MealType,
    val selectedDietType: DietType,
)

 class  A{}
enum class DietType(val value: String) {
    GLUTEN_FREE("Gluten Free"),
    KETOGENIC("Ketogenic"),
    VEGETARIAN("Vegetarian"),
    LACTO_VEGETERIAN("Lacto-Vegetarian"),
    OVO_VEGETERIAN("Ovo-Vegetarian"),
    VEGAN("Vegan"),
    PESCETARIAN("Pescetarian"),
    PALEO("Paleo"),
    PRIMAL("Primal"),
    LOW_FODMAP("Low FODMAP"),
    WHOLE30("Whole30")
}


enum class MealType(val value: String) {
    MAIN_COURSE("main course"),
    SIDE_DISH("side dish"),
    DESSERT("dessert"),
    APPETIZER("appetizer"),
    SALAD("salad"),
    BREAD("bread"), BREAKFAST("breakfast"), SOUP("soup"),
    BEVERAGE("beverage"),
    SAUCE("sauce"),
    MARINADE("marinade"),
    FINGERFOOD("fingerfood"),
    SNACK("snack"),
    DRINK("drink")


}

val DIET_TYPE_LIST = listOf<DietType>(
    DietType.GLUTEN_FREE,
    DietType.KETOGENIC,
    DietType.VEGETARIAN,
    DietType.LACTO_VEGETERIAN,
    DietType.OVO_VEGETERIAN,
    DietType.VEGAN,
    DietType.PESCETARIAN,
    DietType.PALEO,
    DietType.PRIMAL,
    DietType.LOW_FODMAP,
    DietType.WHOLE30
)


val MEAL_TYPE_LIST = listOf<MealType>(
    MealType.MAIN_COURSE,
    MealType.SIDE_DISH,
    MealType.DESSERT,
    MealType.APPETIZER,
    MealType.SALAD,
    MealType.BREAD,
    MealType.BEVERAGE,
    MealType.SAUCE,
    MealType.MARINADE,
    MealType.FINGERFOOD,
    MealType.SNACK,
    MealType.DRINK
)
