package com.matheus.cophat.data.local.entity

data class Questionnaire(
    var familyId: String = "",
    var hospital: String? = null,
    var childApplication: ApplicationEntity? = null,
    var parentApplication: ApplicationEntity? = null
)