package com.example.final2.register

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.final2.MainActivity
import com.example.final2.R
import com.example.final2.data.model.LoggedInUser
import com.example.final2.databinding.ActivityRegisterBinding
import com.example.final2.ui.login.LoggedInUserView
import com.example.final2.ui.login.LoginActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class RegisterActivity : AppCompatActivity() {
    private lateinit var registerViewModel: RegisterViewModel
    private lateinit var binding: ActivityRegisterBinding
    private var register: Button? = null
    private var editName: EditText? = null
     private  var editEmail:EditText? = null
    private  var editPassword:EditText? = null
    private  var editConfirmPassword:EditText? = null
    private var mAuth: FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mAuth = FirebaseAuth.getInstance()

        editName = findViewById<View>(R.id.name) as EditText
        editEmail = findViewById<View>(R.id.username) as EditText
        editPassword = findViewById<View>(R.id.password) as EditText
        editConfirmPassword = findViewById<View>(R.id.password_conf) as EditText
        register = findViewById<View>(R.id.register) as Button
        val loading = binding.loading
        val to_login_page = binding.toLoginPage
        if (to_login_page != null) {
            to_login_page.setOnClickListener{
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)}
        }
        registerViewModel = ViewModelProvider(this, RegisterViewModelFactory())
            .get(RegisterViewModel::class.java)


            register!!.setOnClickListener {
                loading.visibility = View.VISIBLE
                reg()
            }}


    private fun reg() {
            val name: String = editName?.text.toString().trim()
            val username: String = editEmail?.text.toString().trim()
            val password: String = editPassword?.text.toString().trim()
            println(name + username + password)
            val confirmPassword: String = editConfirmPassword?.getText().toString()

            if (name.isEmpty()) {
                editName?.error = "Name is required"
                editName?.requestFocus()
                return
            }
            if (username.isEmpty()) {
                editEmail?.error = "Email is required"
                editEmail?.requestFocus()
                return
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(username).matches()) {
                editEmail?.error = "Email form is invalid"
                editEmail?.requestFocus()
                return
            }
            if (password.isEmpty()) {
                editPassword?.error = "Password is required"
                editPassword?.requestFocus()
                return
            }
            if (password.length < 6) {
                editPassword?.error = "Minimal password length should be 6 characters"
                editPassword?.requestFocus()
                return
            }
            if (confirmPassword.isEmpty()) {
                editConfirmPassword?.error = "Confirming password is required"
                editConfirmPassword?.requestFocus()
                return
            }

            if (password != confirmPassword) {
                editConfirmPassword?.error = "Passwords don't match"
                editConfirmPassword?.requestFocus()
                return
            }
        mAuth?.createUserWithEmailAndPassword(username, password)
            ?.addOnCompleteListener(OnCompleteListener { task: Task<AuthResult?> ->
                if (task.isSuccessful) {
                    val user = LoggedInUser(name, username, password)
                    FirebaseDatabase.getInstance().getReference("Users")
                        .child(FirebaseAuth.getInstance().currentUser!!.uid)
                        .setValue(user)

                    val fireUser = FirebaseAuth.getInstance().currentUser
                    fireUser!!.sendEmailVerification()
                    Toast.makeText(
                        this@RegisterActivity,
                        "User has been registered successfully. Please check your email to verify your account",
                        Toast.LENGTH_LONG
                    ).show()
                    callMain()
                } else {
                    Toast.makeText(
                        this@RegisterActivity,
                        "Failed to register",
                        Toast.LENGTH_LONG
                    )
                        .show()
                }
            })}

    private fun callMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun updateUiWithUser(model: LoggedInUserView?) {
        val welcome = getString(R.string.welcome)
        val displayName = model?.displayName
        Toast.makeText(
            applicationContext,
            "$welcome $displayName",
            Toast.LENGTH_LONG
        ).show()
    }

    private fun showLoginFailed(@StringRes errorString: Int) {
        Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
    }
}

/**
 * Extension function to simplify setting an afterTextChanged action to EditText components.
 */
fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}
