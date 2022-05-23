package com.example.final2.register

import com.example.final2.ui.login.LoggedInUserView

data class RegisterResult (
    val success: LoggedInUserView? = null,
    val error: Int? = null
)