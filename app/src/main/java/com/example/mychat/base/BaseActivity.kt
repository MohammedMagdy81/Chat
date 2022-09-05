package com.example.mychat.base

import android.app.ProgressDialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<VM:BaseViewModel<*>,DB:ViewDataBinding>:AppCompatActivity() {
    lateinit var viewModel:VM
    lateinit var viewBinding:DB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel=inilazeViewModel()
        viewBinding=DataBindingUtil.setContentView(this,getLayoutId())
        viewModel.messageLiveData.observe(this) {message->
        showDialog(message=message, posActionName = "Ok", posAction = {dialog,which->
            dialog.dismiss()
        })
        }
        viewModel.showloading.observe(this) {show->
            if (show){
                showProgressDialog("Loading ...")
            }else{
                hideProgressDialog()
            }

        }
    }


    abstract fun getLayoutId(): Int
    abstract fun inilazeViewModel(): VM

    fun makeToast(message:String){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
    var dialog : ProgressDialog?=null
    fun showProgressDialog(message:String){
        dialog=ProgressDialog(this)
        dialog!!.setCancelable(false)
        dialog!!.setMessage(message)
        dialog!!.show()

    }
    private fun hideProgressDialog() {
        dialog!!.dismiss()
    }


    fun showDialog(message:String?=null ,title:String?=null,
                    posActionName:String?=null
                   ,posAction:DialogInterface.OnClickListener?=null,
                    negActionName:String?=null,
                   negAction:DialogInterface.OnClickListener?=null ,
                    isCancelable:Boolean= true){
        val dialog=AlertDialog.Builder(this).apply {
            setMessage(message)
            setTitle(title)
            setPositiveButton(posActionName,posAction)
            setNegativeButton(negActionName,negAction)
            setCancelable(isCancelable)
        }
        dialog.show()

    }
}