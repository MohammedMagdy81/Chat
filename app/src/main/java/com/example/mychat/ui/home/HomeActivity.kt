package com.example.mychat.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.mychat.R
import com.example.mychat.base.BaseActivity
import com.example.mychat.databinding.ActivityHomeBinding

class HomeActivity : BaseActivity<HomeViewModel,ActivityHomeBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding.vm=viewModel

    }

    override fun getLayoutId(): Int {
        return R.layout.activity_home
    }

    override fun inilazeViewModel(): HomeViewModel {
       return ViewModelProvider(this).get(HomeViewModel::class.java)
    }
}