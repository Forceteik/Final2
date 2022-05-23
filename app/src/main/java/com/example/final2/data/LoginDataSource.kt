package com.example.final2.data

import com.example.final2.data.model.LoggedInUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.io.IOException

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {



    fun logout() {
        Firebase.auth.signOut()
    }
}