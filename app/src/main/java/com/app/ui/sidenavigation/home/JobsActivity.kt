package com.app.ui.sidenavigation.home

import android.os.Bundle
import androidx.activity.viewModels
import com.app.bases.BaseActivity
import com.app.databinding.ActivityJobsBinding
import com.app.ui.main.MainViewModel
import kotlin.getValue

class JobsActivity : BaseActivity<ActivityJobsBinding, MainViewModel>() {
    override val mViewModel: MainViewModel by viewModels()


    override fun initBinding() = ActivityJobsBinding.inflate(layoutInflater)

    override fun initViews(savedInstanceState: Bundle?) {

        val career = intent.getStringExtra("career")

        mViewBinding.tvTitle.text = "$career Jobs"

        showJobs(career)
    }

    override fun addViewModelObservers() {
    }

    override fun attachListens() {
    }
    private fun showJobs(career: String?) {
        val options = arrayOf(
            "LinkedIn",
            "Rozee.pk",
            "Indeed",
            "Glassdoor",
            "Google Jobs"
        )

        val urls = when (career) {

            "Software Engineer" -> arrayOf(
                "https://www.linkedin.com/jobs/search/?keywords=software%20engineer",
                "https://www.rozee.pk/job/jsearch/q/Software+Engineer",
                "https://pk.indeed.com/jobs?q=software+engineer",
                "https://www.google.com/search?q=software+engineer+jobs"
            )

            "UI/UX Designer" -> arrayOf(
                "https://www.linkedin.com/jobs/search/?keywords=ui%20ux%20designer",
                "https://www.rozee.pk/job/jsearch/q/UI+UX+Designer",
                "https://pk.indeed.com/jobs?q=ui+ux+designer",
                "https://www.google.com/search?q=ui+ux+designer+jobs"
            )

            "Data Analyst" -> arrayOf(
                "https://www.linkedin.com/jobs/search/?keywords=data%20analyst",
                "https://www.rozee.pk/job/jsearch/q/Data+Analyst",
                "https://pk.indeed.com/jobs?q=data+analyst",
                "https://www.google.com/search?q=data+analyst+jobs"
            )

            // ✅ NEW
            "Teacher / HR Manager" -> arrayOf(
                "https://www.linkedin.com/jobs/search/?keywords=teacher%20hr%20manager",
                "https://www.rozee.pk/job/jsearch/q/Teacher+HR+Manager",
                "https://pk.indeed.com/jobs?q=teacher+hr+manager",
                "https://www.google.com/search?q=teacher+hr+manager+jobs"
            )

            "Business Analyst" -> arrayOf(
                "https://www.linkedin.com/jobs/search/?keywords=business%20analyst",
                "https://www.rozee.pk/job/jsearch/q/Business+Analyst",
                "https://pk.indeed.com/jobs?q=business+analyst",
                "https://www.google.com/search?q=business+analyst+jobs"
            )

            "General Career Advisor" -> arrayOf(
                "https://www.linkedin.com/jobs/search/?keywords=career%20advisor",
                "https://www.rozee.pk/job/jsearch/q/Career+Advisor",
                "https://pk.indeed.com/jobs?q=career+advisor",
                "https://www.google.com/search?q=career+advisor+jobs"
            )

            else -> arrayOf(
                "https://www.google.com/search?q=jobs",
                "https://www.google.com/search?q=jobs",
                "https://www.google.com/search?q=jobs",
                "https://www.google.com/search?q=jobs"
            )

        }


        val links = options.zip(urls).joinToString("<br><br>") { (name, url) ->
            "<a href=\"$url\">$name</a>"
        }

        mViewBinding.tvJobs.text = android.text.Html.fromHtml(
            links,
            android.text.Html.FROM_HTML_MODE_LEGACY
        )

        mViewBinding.tvJobs.movementMethod =
            android.text.method.LinkMovementMethod.getInstance()

    }
}