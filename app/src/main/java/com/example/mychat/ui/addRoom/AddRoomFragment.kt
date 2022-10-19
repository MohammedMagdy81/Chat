package com.example.mychat.ui.addRoom

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mychat.R
import com.example.mychat.base.BaseFragment
import com.example.mychat.databinding.ActivityAddRoomBinding

class AddRoomFragment : BaseFragment<AddRoomViewModel, ActivityAddRoomBinding>(), AddRoomNavigator {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.vm = viewModel
        viewModel.navigator = this
        viewModel.roomAdded.observe(viewLifecycleOwner) { roomAdded ->
            showDialog(message = "Room Added Successfully", posActionName = "Ok",
                posAction = { dialogInterface, i ->
                    dialogInterface.dismiss()
                   // findNavController().navigate(R.id.action_back_to_home)




                    // TODO: Replace Finish with back action ..
                }
            )

        }

    }


    override fun getLayoutId(): Int {
        return R.layout.activity_add_room
    }

    override fun initializeViewModel(): AddRoomViewModel {
        return ViewModelProvider(this).get(AddRoomViewModel::class.java)
    }
}