package com.app.ui.sidenavigation.home

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.app.bases.BaseActivity
import com.app.databinding.ActivityAssessmentsBinding
import com.app.databinding.ActivityOptionsBinding
import com.app.ui.main.MainViewModel
import com.app.ui.main.WelcomeActivity
import kotlin.getValue

class OptionsActivity : BaseActivity<ActivityOptionsBinding , MainViewModel>() {
    override val mViewModel: MainViewModel by viewModels()

    override fun initBinding() = ActivityOptionsBinding.inflate(layoutInflater)

    override fun initViews(savedInstanceState: Bundle?) {
    }

    override fun addViewModelObservers() {
    }

    override fun attachListens() {
//        mViewBinding.btnCareerAssessment.setOnClickListener {
//            startActivity(Intent(this, AssessmentsActivity::class.java))
//        }

    }
}