package com.example.perforatormobile.data.repositories.data_classes

import com.example.perforatormobile.domain.entities.Person

data class SubordinatesBySelfReviewStatus constructor(
    val subordinatesNotFinishedSelfReview: List<Person>,
    val notVerifiedSubordinates: List<Person>,
    val verifiedSubordinates: List<Person>,
    )