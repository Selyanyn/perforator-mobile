package com.example.perforatormobile.domain.entities

data class Grade(
    val id: Int = 0,
    val categoryId: String = "",
    val categoryName: String = "",
    val categoryDescription: String = "",
    val grade: Int = 0,
    val comment: String? = null
)