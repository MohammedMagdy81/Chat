package com.example.mychat.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import com.example.mychat.R
import com.example.mychat.base.BaseActivity
import com.example.mychat.databinding.ActivityHomeBinding
import com.example.mychat.ui.addRoom.AddRoomActivity

class HomeActivity : BaseActivity<HomeViewModel, ActivityHomeBinding>(), HomeNavigator {
    lateinit var roomsAdapter: RoomsRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding.vm = viewModel
        viewModel.navigator = this
        setUpToolBar()
        setUpViews()
        observeLiveData()
    }

    private fun setUpToolBar() {
        val toolbar = viewBinding.toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setDisplayShowHomeEnabled(true);
    }

    private fun observeLiveData() {
        viewModel.roomsLiveData.observe(this) {
            roomsAdapter.changeData(it)
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.getRoomsList()
    }

    private fun setUpViews() {
        roomsAdapter = RoomsRecyclerAdapter(listOf())
        viewBinding.roomsAddedRecyclerView.apply {
            setHasFixedSize(true)
            adapter = roomsAdapter
        }


    }

    override fun goToAddRoom() {
        val intent = Intent(this, AddRoomActivity::class.java)
        startActivity(intent)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item)
    }


    override fun getLayoutId(): Int {
        return R.layout.activity_home
    }

    override fun inilazeViewModel(): HomeViewModel {
        return ViewModelProvider(this).get(HomeViewModel::class.java)
    }
}