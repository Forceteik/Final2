package com.example.final2.register

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.final2.R
import com.example.final2.data.Result
import com.example.final2.ui.login.LoggedInUserView
import com.example.final2.ui.login.LoginFormState
import com.example.final2.ui.login.LoginResult

class RegisterViewModel (private val registerRepository: RegisterRepository) : ViewModel() {

    private val _registerForm = MutableLiveData<RegisterFormState>()
    val registerFormState: MutableLiveData<RegisterFormState> = _registerForm
    private val _registerResult = MutableLiveData<RegisterResult>()
    val registerResult: LiveData<RegisterResult> = _registerResult

//    fun register(name: String, username: String, password: String) {
//        // can be launched in a separate asynchronous job
//        val result = registerRepository.register(name, username, password)
//
//        if (result is Result.Success) {
//            _registerResult.value =
//                RegisterResult(success = LoggedInUserView(displayName = result.data.displayName))
//
//        } else {
//            _registerResult.value = RegisterResult(error = R.string.login_failed)
//        }
//    }

    fun registerDataChanged(name: String, username: String, password: String) {
//        if (!isUserNameValid(username)) {
//            _registerForm.value = RegisterFormState(usernameError = R.string.invalid_username)
//        } else
            if (!isPasswordValid(password)) {
            _registerForm.value = RegisterFormState(passwordError = R.string.invalid_password)
        } else if (!isNameValid(name)) {
            _registerForm.value = RegisterFormState(nameError = R.string.invalid_name)
        } else {
            _registerForm.value = RegisterFormState(isDataValid = true)
        }
    }

    private fun isNameValid(name: String): Boolean {
        return name.isNotBlank()
    }

    // A placeholder username validation check
    private fun isUserNameValid(username: String): Boolean {
        return username.isNotBlank()

    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }
}