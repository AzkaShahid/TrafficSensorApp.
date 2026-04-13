package com.app.ui.sidenavigation.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.app.bases.BaseActivity
import com.app.databinding.ActivityAssessmentsBinding
import com.app.preferences.PreferencesHelper
import com.app.ui.main.MainViewModel
import kotlin.getValue

class AssessmentsActivity: BaseActivity<ActivityAssessmentsBinding , MainViewModel>() {
    override val mViewModel: MainViewModel by viewModels()
    private var currentPage = 1

    override fun initBinding() = ActivityAssessmentsBinding.inflate(layoutInflater)


    override fun initViews(savedInstanceState: Bundle?) {

        mViewBinding.btnPrevious.visibility = View.GONE
        mViewBinding.btnSubmit.visibility = View.GONE
        updateStepText()

    }

    override fun addViewModelObservers() {
    }

    override fun attachListens() {

        mViewBinding.btnNext.setOnClickListener {
            when (currentPage) {
                1 -> {
                    mViewBinding.page1.visibility = View.GONE
                    mViewBinding.page2.visibility = View.VISIBLE
                    mViewBinding.btnPrevious.visibility = View.VISIBLE
                    currentPage = 2
                }

                2 -> {
                    mViewBinding.page2.visibility = View.GONE
                    mViewBinding.page3.visibility = View.VISIBLE
                    mViewBinding.btnNext.visibility = View.GONE
                    mViewBinding.btnSubmit.visibility = View.VISIBLE
                    currentPage = 3
                }
            }
            updateStepText()
        }

        mViewBinding.btnPrevious.setOnClickListener {
            when (currentPage) {
                2 -> {
                    mViewBinding.page2.visibility = View.GONE
                    mViewBinding.page1.visibility = View.VISIBLE
                    mViewBinding.btnPrevious.visibility = View.GONE
                    currentPage = 1
                }

                3 -> {
                    mViewBinding.page3.visibility = View.GONE
                    mViewBinding.page2.visibility = View.VISIBLE
                    mViewBinding.btnNext.visibility = View.VISIBLE
                    mViewBinding.btnSubmit.visibility = View.GONE
                    currentPage = 2
                }
            }
            updateStepText()
        }
        mViewBinding.btnSubmit.setOnClickListener {

            var tech = 0
            var creative = 0
            var analytical = 0
            var social = 0
            var business = 0
            if (mViewBinding.q1.isChecked) tech++
            if (mViewBinding.q2.isChecked) creative++
            if (mViewBinding.q3.isChecked) analytical++
            if (mViewBinding.q4.isChecked) social++
            if (mViewBinding.q5.isChecked) business++


            if (mViewBinding.q6.isChecked) tech++
            if (mViewBinding.q7.isChecked) creative++
            if (mViewBinding.q8.isChecked) analytical++
            if (mViewBinding.q9.isChecked) social++
            if (mViewBinding.q10.isChecked) business++


            if (mViewBinding.q11.isChecked) tech++
            if (mViewBinding.q12.isChecked) creative++
            if (mViewBinding.q13.isChecked) analytical++
            if (mViewBinding.q14.isChecked) social++
            if (mViewBinding.q15.isChecked) business++

            val personality = getPersonalityType(tech, creative, analytical, social, business)

            val gpa = intent.getStringExtra("gpa") ?: "0"
            val program = intent.getStringExtra("program") ?: ""

            val career = getCareer(personality, program, gpa)
            PreferencesHelper.setPrefs("USER_PERSONALITY", personality)
            PreferencesHelper.setPrefs("USER_CAREER", career)
            val intent = Intent()
            intent.putExtra("personality", personality)
            intent.putExtra("career", career)

            setResult(RESULT_OK, intent)
            finish()
        }
    }
    private fun getPersonalityType(
        tech: Int,
        creative: Int,
        analytical: Int,
        social: Int,
        business: Int
    ): String {

        val max = listOf(tech, creative, analytical, social, business).maxOrNull()

        return when (max) {
            tech -> "Technical"
            creative -> "Creative"
            analytical -> "Analytical"
            social -> "Social"
            business -> "Business"
            else -> "General"
        }
    }


    private fun getCareer(personality: String, program: String, gpa: String): String {

        val gpaValue = gpa.toDoubleOrNull() ?: 0.0

        return when (personality) {

            "Technical" -> {
                if (gpaValue >= 3.0) "Software Engineer"
                else "IT Support Specialist"
            }

            "Creative" -> "UI/UX Designer"

            "Analytical" -> {
                if (program == "Physics") "Research Scientist"
                else "Data Analyst"
            }

            "Social" -> "Teacher / HR Manager"

            "Business" -> "Business Analyst"

            else -> "General Career Advisor"
        }

    }

    private fun getCareer(personality: String): String {
        return when (personality) {
            "Technical" -> "Software Engineer"
            "Creative" -> "UI/UX Designer"
            "Analytical" -> "Data Analyst"
            "Social" -> "Teacher / HR"
            "Business" -> "Business Analyst"
            else -> "General Advisor"
        }
    }
    private fun updateStepText() {
        mViewBinding.tvStep.text = "Step $currentPage/3"
    }
}
