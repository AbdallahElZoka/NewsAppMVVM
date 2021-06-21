package com.route.islamigsun.base

import android.app.ProgressDialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel

abstract class BaseActivity <DB:ViewDataBinding,VM:ViewModel> :AppCompatActivity() {

    lateinit var viewDataBinding: DB
    lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding = DataBindingUtil.setContentView(this,getLayoutId())
        viewModel= initializeViewModel()
    }
    abstract fun getLayoutId():Int
    abstract fun initializeViewModel():VM

    fun makeToast(message:String){
        Toast.makeText(this,"Hello user", Toast.LENGTH_LONG).show()
    }
    fun makeToast(messageId:Int){
        Toast.makeText(this,messageId, Toast.LENGTH_LONG).show()
    }

    fun showProgressDialoge(message:String){
      val dialoge =   ProgressDialog(this)
        dialoge.setCancelable(false)
        dialoge.setMessage(message)
        dialoge.show()
    }

    // snack bar
    // dialoges
    fun showDialoge(title:String?=null,
                    message:String,
                    posActionName:String?=null,
                    posAction: DialogInterface.OnClickListener?=null,
                    negActionName:String?=null,
                    negAction: DialogInterface.OnClickListener?=null){
        val builder = AlertDialog.Builder(this)
        builder.setMessage(message)
        builder.setTitle(title)
        builder.setPositiveButton(posActionName,posAction)
        builder.setNegativeButton(negActionName,negAction)
        builder.show()
    }
    fun showDialoge(titleId:Int?=null,
                    messageId:Int,
                    posActionName:Int?=null,
                    posAction: DialogInterface.OnClickListener?=null,
                    negActionName:Int?=null,
                    negAction: DialogInterface.OnClickListener?=null,
                    isCancelable:Boolean=true){
        val builder = AlertDialog.Builder(this)
        builder.setMessage(messageId)
        builder.setCancelable(isCancelable)
        if(titleId!=null)
            builder.setTitle(titleId)
        if (posActionName!=null)
            builder.setPositiveButton(posActionName,posAction)
        if (negActionName!=null)
            builder.setNegativeButton(negActionName,negAction)

        builder.show()
    }

}