package com.example.perforatormobile.domain.entities

data class Grade(
    val id: Int = 0,
    val category: Category = Category(),
    val grade: Int = 0,
    val comment: String? = null
)