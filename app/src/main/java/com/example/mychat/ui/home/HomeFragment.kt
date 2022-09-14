package com.example.mychat.ui.home

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mychat.R
import com.example.mychat.base.BaseFragment
import com.example.mychat.database.model.Room
import com.example.mychat.databinding.ActivityHomeBinding

class HomeFragment : BaseFragment<HomeViewModel, ActivityHomeBinding>(), HomeNavigator {
    lateinit var roomsAdapter: RoomsRecyclerAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.vm = viewModel
        viewModel.navigator = this
        setUpViews()
        observeLiveData()
    }


    private fun observeLiveData() {
        viewModel.roomsLiveData.observe(viewLifecycleOwner) {
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
            adapter = roomsAdapter

        }
        roomsAdapter.onItemClickListener = object : RoomsRecyclerAdapter.OnItemClickListener {
            override fun onItemClick(position: Int, room: Room) {
                val action = HomeFragmentDirections.actionHomeFragmentToRoomDetailsFragment(room)
                findNavController().navigate(action)

            }

        }

    }

    override fun goToAddRoom() {

        findNavController().navigate(R.id.action_homeFragment_to_addRoomFragment)

    }

    override fun getLayoutId(): Int {
        return R.layout.activity_home
    }

    override fun inilazeViewModel(): HomeViewModel {
        return ViewModelProvider(this).get(HomeViewModel::class.java)
    }

}