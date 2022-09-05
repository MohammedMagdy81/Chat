package com.example.mychat.ui.addRoom

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.mychat.R
import com.example.mychat.base.BaseActivity
import com.example.mychat.databinding.ActivityAddRoomBinding

class AddRoomActivity : BaseActivity<AddRoomViewModel,ActivityAddRoomBinding>() ,AddRoomNavigator{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding.vm=viewModel
        viewModel.navigator=this
        viewModel.roomAdded.observe(this) {roomAdded->
            showDialog(message = "Room Added Secussfully", posActionName = "Ok",
            posAction = { dialogInterface, i ->
                dialogInterface.dismiss()
                finish()
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