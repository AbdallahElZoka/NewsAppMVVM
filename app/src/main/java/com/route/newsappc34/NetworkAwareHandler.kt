package com.route.newsappc34

import android.content.Context
import android.net.ConnectivityManager
import androidx.core.content.ContextCompat.getSystemService


interface NetworkAwareHandler {
   fun isOnline():Boolean
}
class NetworkAwareHandlerImpl(val context: Context):NetworkAwareHandler{

   override fun isOnline(): Boolean {
      val cm =
         context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?

      return cm!!.activeNetworkInfo != null && cm.activeNetworkInfo!!.isConnected

   }
   companion object{
      var myInstance:NetworkAwareHandler?=null
      fun init(context:Context){
         myInstance = NetworkAwareHandlerImpl(context)
      }
      fun getInstance():NetworkAwareHandler{
         return myInstance!!
      }
   }
}