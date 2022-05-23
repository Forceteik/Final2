package com.example.final2.register

data class RegisterFormState(
val usernameError: Int? = null,
val nameError: Int? = null,
val passwordError: Int? = null,
val isDataValid: Boolean = false
)
