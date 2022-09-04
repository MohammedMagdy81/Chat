package com.example.mychat.base

import android.app.ProgressDialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel

abstract class BaseActivity<VM:ViewModel,DB:ViewDataBinding>:AppCompatActivity() {
    lateinit var viewModel:VM
    lateinit var viewBinding:DB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel=inilazeViewModel()
        viewBinding=DataBindingUtil.setContentView(this,getLayoutId())
    }

    abstract fun getLayoutId(): Int
    abstract fun inilazeViewModel(): VM

    fun makeToast(message:String){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    fun showProgressDialog(message:String){
        val dialog= ProgressDialog(this)
        dialog.setCancelable(false)
        dialog.setMessage(message)
        dialog.show()

    }

    fun showDialog(title:String?=null,message:String? ,
                    posActionName:String?=null
                   ,posAction:DialogInterface.OnClickListener?=null,
                    negActionName:String?,negAction:DialogInterface.OnClickListener?=null){
        val dialog=AlertDialog.Builder(this).apply {
            setMessage(message)
            setTitle(title)
            setPositiveButton(posActionName,posAction)
            setNegativeButton(negActionName,negAction)
        }
        dialog.show()

    }
}