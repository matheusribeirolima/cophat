package com.matheus.cophat.data.local.entity

data class Questionnaire(
    var familyId: String = "",
    var childApplication: ApplicationEntity? = null,
    var parentApplication: ApplicationEntity? = null
)