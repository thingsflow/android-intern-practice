package com.thingsflow.internapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.ProgressBar
import androidx.constraintlayout.widget.ConstraintLayout
import com.thingsflow.internapplication.databinding.MainActivityBinding
import com.thingsflow.internapplication.ui.main.home.HomeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var _binding: MainActivityBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        _binding = MainActivityBinding.inflate(layoutInflater)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, HomeFragment.newInstance())
                .commitNow()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    fun showProgress() {
        binding.progressLoading.visibility = View.VISIBLE
        binding.layoutProgress.visibility = View.VISIBLE
    }

    fun hideProgress() {
        binding.progressLoading.visibility = View.GONE
        binding.layoutProgress.visibility = View.GONE
    }
}