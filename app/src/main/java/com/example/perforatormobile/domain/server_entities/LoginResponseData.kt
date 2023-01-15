package com.example.perforatormobile.domain.server_entities

data class LoginResponseData(
    val status: String,
    val token_f: String,
    val token_f_lifetime: String
) {
    override fun toString(): String = status + token_f + token_f_lifetime
}