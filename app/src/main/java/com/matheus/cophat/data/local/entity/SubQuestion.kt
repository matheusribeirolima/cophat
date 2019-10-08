package com.matheus.cophat.data.local.entity

data class SubQuestion(
    var id: Int? = null,
    var category: CategoryType? = null,
    var statement: String? = null,
    var statementMale: String? = null,
    var statementFemale: String? = null,
    var chosenAlternatives: List<SubAnswer>? = null,
    var alternatives: List<SubAnswer>? = null
)