package com.example.mychat.base

import android.app.ProgressDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<VM:BaseViewModel<*>,DB: ViewDataBinding>:Fragment() {
    lateinit var viewModel:VM
    lateinit var viewBinding:DB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding= DataBindingUtil.inflate(inflater,getLayoutId(),container,false)
        viewModel=initializeViewModel()

        viewModel.messageLiveData.observe(viewLifecycleOwner) {message->
            showDialog(message=message, posActionName = "Ok", posAction = {dialog,which->
                dialog.dismiss()
            })
        }

        viewModel.showloading.observe(viewLifecycleOwner) {show->
            if (show){
                showProgressDialog("Loading ...")
            }else{
                hideProgressDialog()
            }

        }
        return viewBinding.root
    }


    abstract fun getLayoutId(): Int
    abstract fun initializeViewModel(): VM



    fun showDialog(message:String?=null, title:String?=null,
                   posActionName:String?=null
                   , posAction: DialogInterface.OnClickListener?=null,
                   negActionName:String?=null,
                   negAction: DialogInterface.OnClickListener?=null,
                   isCancelable:Boolean= true){
        val dialog= AlertDialog.Builder(requireContext()).apply {
            setMessage(message)
            setTitle(title)
            setPositiveButton(posActionName,posAction)
            setNegativeButton(negActionName,negAction)
            setCancelable(isCancelable)
        }
        dialog.show()

    }

    var dialog : ProgressDialog?=null
    fun showProgressDialog(message:String){
        dialog= ProgressDialog(requireContext())
        dialog!!.setCancelable(false)
        dialog!!.setMessage(message)
        dialog!!.show()

    }
    private fun hideProgressDialog() {
        dialog!!.dismiss()
    }

    fun makeToast(message:String){
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }



}