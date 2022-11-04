package com.joshgm3z.taskfactory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.joshgm3z.taskfactory.viewmodel.DashboardViewModel

class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val viewModel: DashboardViewModel by viewModels()
    }
}