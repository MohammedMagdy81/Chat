package com.example.mychat.ui.addRoom

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.mychat.R
import com.example.mychat.base.BaseFragment
import com.example.mychat.databinding.ActivityAddRoomBinding

class AddRoomFragment:BaseFragment<AddRoomViewModel, ActivityAddRoomBinding>(),AddRoomNavigator {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.vm=viewModel
        viewModel.navigator=this
        viewModel.roomAdded.observe(viewLifecycleOwner) {roomAdded->
            showDialog(message = "Room Added Secussfully", posActionName = "Ok",
                posAction = { dialogInterface, i ->
                    dialogInterface.dismiss()
                    requireActivity().finish()

                    // TODO: Replace Finish with back action ..  
                }, isCancelable = false)

        }
    }



    override fun getLayoutId(): Int {
        return R.layout.activity_add_room
    }

    override fun inilazeViewModel(): AddRoomViewModel {
        return ViewModelProvider(this).get(AddRoomViewModel::class.java)
    }
}