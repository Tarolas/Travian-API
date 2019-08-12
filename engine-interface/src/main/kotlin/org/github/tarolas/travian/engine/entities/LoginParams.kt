package org.github.tarolas.travian.api.entities

data class LoginParams(
        val username: String,
        val password: String,
        val server: String
)
