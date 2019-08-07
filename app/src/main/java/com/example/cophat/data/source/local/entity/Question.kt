package com.example.cophat.data.source.local.entity

data class Question(
    val id: Int,
    val category: Category,
    val statement: String,
    val chosenAnswer: Answer,
    val alternatives: List<Answer>,
    val subQuestions: List<SubQuestion>
)