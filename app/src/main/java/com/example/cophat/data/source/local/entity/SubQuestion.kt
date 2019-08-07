package com.example.cophat.data.source.local.entity

data class SubQuestion(
    val id: Int,
    val category: Category,
    val statement: String,
    val chosenAnswer: SubAnswer,
    val alternatives: List<SubAnswer>
)