package com.app.ui.sidenavigation.home

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.app.bases.BaseActivity
import com.app.databinding.ActivitySignUpBinding
import com.app.preferences.PreferencesHelper
import com.app.ui.login.LoginActivity
import com.app.ui.main.MainViewModel
import kotlin.getValue

class SignUpActivity : BaseActivity<ActivitySignUpBinding, MainViewModel>() {

    override val mViewModel: MainViewModel by viewModels()

    override fun initBinding() = ActivitySignUpBinding.inflate(layoutInflater)


    override fun initViews(savedInstanceState: Bundle?) {


    }

    override fun addViewModelObservers() {
    
    }

    override fun attachListens() {
        mViewBinding.btnSignUp.setOnClickListener {

            val email = mViewBinding.etUserName.text.toString().trim()
            val password = mViewBinding.etPassword.text.toString().trim()
            val confirmPassword = mViewBinding.etReEnterPassword.text.toString().trim()

            // ✅ Empty check
            if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // ✅ Email validation
            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                mViewBinding.etUserName.error = "Enter valid email"
                return@setOnClickListener
            }

            // ✅ Password length
            if (password.length < 6) {
                mViewBinding.etPassword.error = "Password must be at least 6 characters"
                return@setOnClickListener
            }

            // ✅ Match password
            if (password != confirmPassword) {
                mViewBinding.etReEnterPassword.error = "Passwords do not match"
                return@setOnClickListener
            }

            // ✅ SAVE DATA (IMPORTANT 🔥)
            PreferencesHelper.saveLoginCredentials(email, password, true)

            Toast.makeText(this, "Signup Successful!", Toast.LENGTH_SHORT).show()

            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }       }
}