package com.example.final2.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModelProvider
import com.example.final2.MainActivity
import com.example.final2.R
import com.example.final2.databinding.ActivityLoginBinding
import com.example.final2.register.RegisterActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var binding: ActivityLoginBinding
    private var editEmail: EditText? = null
    private var editPassword:EditText? = null
    private var login: Button? = null
    private var mAuth: FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mAuth = FirebaseAuth.getInstance()
        editEmail = findViewById<View>(R.id.username) as EditText
        editPassword = findViewById<View>(R.id.password) as EditText
        login = findViewById<View>(R.id.login) as Button
        val loading = binding.loading
        binding.toRegisterPage?.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
        loginViewModel = ViewModelProvider(this, LoginViewModelFactory())
            .get(LoginViewModel::class.java)


            login!!.setOnClickListener {
                loading.visibility = View.VISIBLE
               log()
            }
        }

    private fun log() {
        val email: String = editEmail?.text.toString().trim()
        val password: String = editPassword?.getText().toString()

        if (email.isEmpty()) {
            editEmail?.setError("Email is required")
            editEmail?.requestFocus()
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editEmail?.setError("Email is not valid")
            editEmail?.requestFocus()
            return
        }
        if (password.isEmpty()) {
            editPassword?.setError("Password is required")
            editPassword?.requestFocus()
            return
        }
        mAuth?.signInWithEmailAndPassword(email, password)
            ?.addOnCompleteListener(OnCompleteListener { task: Task<AuthResult?> ->
                if (task.isSuccessful) {
                    val user = FirebaseAuth.getInstance().currentUser

                    callMain()

                } else {
                    Toast.makeText(this@LoginActivity, "Failed to login", Toast.LENGTH_LONG).show()
                }
            })
    }


fun callMain() {
    val intent = Intent(this, MainActivity::class.java)
    startActivity(intent)
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
}}