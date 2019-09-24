package com.matheus.cophat.data.local.entity

enum class CategoryType(val category: String) {
    CATEGORY_ONE("Compreensão doença"),
    CATEGORY_TWO("Internação"),
    CATEGORY_THREE("Realização e êxito do tratamento"),
    CATEGORY_FOUR("Efeitos colaterais"),
    CATEGORY_FIVE("Expectativa do retorno à escola")
}

data class Category(val categoryType: String)