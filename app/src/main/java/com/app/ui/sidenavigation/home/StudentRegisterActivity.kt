package com.app.ui.sidenavigation.home

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContentProviderCompat.requireContext
import com.app.R
import com.app.bases.BaseActivity
import com.app.databinding.ActivitySplashBinding
import com.app.databinding.ActivityStudentRegistrationBinding
import com.app.preferences.PreferencesHelper
import com.app.ui.main.MainActivity
import com.app.ui.main.MainViewModel
import kotlin.getValue
import kotlin.toString

class StudentRegisterActivity : BaseActivity<ActivityStudentRegistrationBinding , MainViewModel>() {
    override val mViewModel: MainViewModel by viewModels()

    override fun initBinding() = ActivityStudentRegistrationBinding.inflate(layoutInflater)

    override fun initViews(savedInstanceState: Bundle?) {
        setupSpinner1()
        setupSpinner2()

    }

    override fun addViewModelObservers() {
    }

    override fun attachListens() {

        mViewBinding.btnRegister.setOnClickListener {

            val name = mViewBinding.etName.text.toString()
            val gpa = mViewBinding.etGpa.text.toString()
            val email = mViewBinding.etEmail.text.toString()
            val semester = mViewBinding.spinnerSemester.selectedItem.toString()
            val program = mViewBinding.spinnerProgram.selectedItem.toString()
            if (name.isNullOrEmpty() || name == "null" ||
                gpa.isNullOrEmpty() || gpa.contains("null") ||
                program.isNullOrEmpty() || program == "null"
            ) {

                Toast.makeText(this, "Please fill all fields!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val intent = Intent()

            intent.putExtra("name", name)
            intent.putExtra("gpa", gpa)
            intent.putExtra("semester", semester)
            intent.putExtra("email", email)
            intent.putExtra("program", program)

            PreferencesHelper.setPrefs("USER_NAME", name)
            PreferencesHelper.setPrefs("USER_GPA", gpa)
            PreferencesHelper.setPrefs("USER_EMAIL", email)
            PreferencesHelper.setPrefs("USER_SEMESTER", semester)
            PreferencesHelper.setPrefs("USER_PROGRAM", program)

            setResult(RESULT_OK, intent)

            finish()
        }
    }
    private fun setupSpinner1() {

        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.study_programs,
            android.R.layout.simple_spinner_item
        )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        mViewBinding.spinnerProgram.adapter = adapter
    }
    private fun setupSpinner2() {

        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.semester,
            android.R.layout.simple_spinner_item
        )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        mViewBinding.spinnerSemester.adapter = adapter
    }

}
